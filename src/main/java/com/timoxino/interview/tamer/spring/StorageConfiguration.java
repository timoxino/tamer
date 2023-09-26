package com.timoxino.interview.tamer.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Configuration
public class StorageConfiguration {
    @Bean
    public Storage storage() {
        return StorageOptions.newBuilder().setProjectId("timoxino").build().getService();
    }
}
