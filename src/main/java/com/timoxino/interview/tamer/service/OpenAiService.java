package com.timoxino.interview.tamer.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;

@Service
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
    com.theokanning.openai.service.OpenAiService openAiClient;

    @Override
    public Integer evaluateSeniorityLevel(String cv, String role) {
        String prompt = PROMPT_TEMPLATE_EVALUATE_SENIORITY_LEVEL.replace("{role}", role).replace("{cv}", cv);
        return Integer.valueOf(initiateCompletion(prompt));
    }

    @Override
    public List<String> detectSkills(String cv, String role) {
        String prompt = PROMPT_TEMPLATE_DETECT_SKILLS.replace("{role}", role).replace("{cv}", cv);
        return Arrays.asList(initiateCompletion(prompt).split(","));
    }

    private String initiateCompletion(String prompt) {
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder().model("gpt-3.5-turbo")
                .temperature(0.0).n(1).messages(Arrays.asList(new ChatMessage("user", prompt)))
                .build();
        ChatCompletionResult completion = openAiClient.createChatCompletion(chatCompletionRequest);
        return completion.getChoices().get(0).getMessage().getContent();
    }

}
