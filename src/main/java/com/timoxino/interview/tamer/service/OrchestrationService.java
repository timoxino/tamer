package com.timoxino.interview.tamer.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timoxino.interview.shared.dto.CandidateBaseMessage;
import com.timoxino.interview.shared.dto.CandidateExtractedSkillsMessage;
import com.timoxino.interview.tamer.spring.PubSubSenderConfiguration.PubSubSkillsGateway;

@Service
public class OrchestrationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrchestrationService.class);

    @Autowired
    CompletionService completionService;

    @Autowired
    StorageService storageService;

    @Autowired
    PubSubSkillsGateway pubSubSkillsGateway;

    public void augmentProfile(CandidateBaseMessage profile) throws IOException {
        String cvFileName = profile.getCvUri();
        LOGGER.info("Augmenting the profile for the CV {}", cvFileName);
        
        String cvContent = storageService.readCvFile(cvFileName);
        List<String> skillsDetected = completionService.detectSkills(cvContent);
        Integer seniorityLevelEvaluated = completionService.evaluateSeniorityLevel(cvContent);

        CandidateExtractedSkillsMessage message = new CandidateExtractedSkillsMessage();
        message.setCvUri(cvFileName);
        message.setRole(profile.getRole());
        message.setLvlExpected(profile.getLvlExpected());
        message.setSkills(skillsDetected);
        message.setLvlEstimated(seniorityLevelEvaluated);
        pubSubSkillsGateway.sendSkillsToPubSub(message);
    }
}
