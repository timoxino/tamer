package com.timoxino.interview.tamer.spring;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.theokanning.openai.service.OpenAiService;

@Configuration
public class OpenAiConfiguration {

    @Bean
    OpenAiService openAiClient(@Value("${openai.token}") String openAiToken) {
        return new OpenAiService(openAiToken, Duration.ofSeconds(60));
    }
}
