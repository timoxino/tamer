package com.timoxino.interview.tamer.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timoxino.interview.shared.dto.CandidateBaseMessage;
import com.timoxino.interview.shared.dto.CandidateExtractedSkillsMessage;
import com.timoxino.interview.tamer.spring.PubSubSenderConfiguration.PubSubSkillsGateway;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrchestrationService {

    @Autowired
    CompletionService completionService;

    @Autowired
    StorageService storageService;

    @Autowired
    PubSubSkillsGateway pubSubSkillsGateway;

    public void augmentProfile(CandidateBaseMessage profile) throws IOException {
        String cvFileName = profile.getCvUri();
        log.info("Augmenting the profile for the CV file '{}'", cvFileName);
        
        String cvContent = storageService.readCvFile(cvFileName);
        log.info("CV file '{}' was read", cvFileName);
        List<String> skillsDetected = completionService.detectSkills(cvContent, profile.getRole());
        log.info("{} skills were detected in CV file '{}'", skillsDetected.size(), cvFileName);
        Integer seniorityLevelEvaluated = completionService.evaluateSeniorityLevel(cvContent, profile.getRole());
        log.info("{} seniority level detected in CV file '{}'", seniorityLevelEvaluated, cvFileName);

        CandidateExtractedSkillsMessage message = new CandidateExtractedSkillsMessage();
        message.setCvUri(cvFileName);
        message.setRole(profile.getRole());
        message.setLvlExpected(profile.getLvlExpected());
        message.setSkills(skillsDetected);
        message.setLvlEstimated(seniorityLevelEvaluated);
        pubSubSkillsGateway.sendSkillsToPubSub(message);
        log.info("Message for CV file {} with extracted skills was sent", cvFileName);
    }
}
