package com.timoxino.interview.tamer.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.timoxino.interview.shared.dto.CandidateExtractedSkillsMessage;
import com.timoxino.interview.tamer.annotation.LocalProfile;
import com.timoxino.interview.tamer.spring.PubSubSenderConfiguration.PubSubSkillsGateway;

@Configuration
@LocalProfile
public class StubConfiguration {

    @Bean
    public PubSubSkillsGateway pubSubSkillsGateway() {
        return new PubSubSkillsGateway() {
            @Override
            public void sendSkillsToPubSub(CandidateExtractedSkillsMessage message) {
                // Auto-generated method stub
            }
        };
    }

    @Bean
    public Storage storage() {
        return StorageOptions.newBuilder().build().getService();
    }
}
