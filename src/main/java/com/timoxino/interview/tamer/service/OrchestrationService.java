package com.timoxino.interview.tamer.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timoxino.interview.shared.dto.CandidateBaseMessage;
import com.timoxino.interview.shared.dto.CandidateExtractedSkillsMessage;

@Service
public class OrchestrationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrchestrationService.class);

    @Autowired
    CompletionService completionService;

    @Autowired
    StorageService storageService;

    public CandidateExtractedSkillsMessage augmentProfile(CandidateBaseMessage message) throws IOException {
        LOGGER.info("Augmenting the profile for the cv {}", message.getCvUri());
        String cv = storageService.readCvFile("Test.txt");
        LOGGER.info("CV content: {}", cv);
        return new CandidateExtractedSkillsMessage();
    }
}
