package com.timoxino.interview.tamer.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.ai.chat.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class OpenAiService implements CompletionService {

    final static String PROMPT_TEMPLATE_EVALUATE_SENIORITY_LEVEL = """
            Identify seniority level of the candidate \
            applying for the technical role of {role} \
            with the following CV delimited with triple backticks.\
            Seniority levels can be determined based on number of java frameworks mentioned \
            and years of experience and can be the following:
            1. Junior
            2. Middle
            3. Senior
            4. Lead

            Provide a single-digit answer that corresponds to the level identified.
            CV: '''{cv}'''
            """;

    final static String PROMPT_TEMPLATE_DETECT_SKILLS = """
            Identify key technical skills which the following CV has \
            for the role of {role} \
            in the following CV delimited with triple backticks. \

            Provide the list of skill names only separated by comma. \

            CV: '''{cv}'''
            """;

    @Autowired
    private final ChatClient chatClient;

    @Override
    public Integer evaluateSeniorityLevel(String cv, String role) {
        String prompt = PROMPT_TEMPLATE_EVALUATE_SENIORITY_LEVEL.replace("{role}", role).replace("{cv}", cv);
        return Integer.valueOf(executeCompletion(prompt));
    }

    @Override
    public List<String> detectSkills(String cv, String role) {
        String prompt = PROMPT_TEMPLATE_DETECT_SKILLS.replace("{role}", role).replace("{cv}", cv);
        return Arrays.asList(executeCompletion(prompt).split(","));
    }

    private String executeCompletion(String prompt) {
        log.info("Completion initiation for the prompt '{}'", prompt.substring(0, prompt.length() / 4));
        String response = chatClient.call(prompt);
        log.info("Completion finished. The response is '{}", response);
        return response;
    }
}
