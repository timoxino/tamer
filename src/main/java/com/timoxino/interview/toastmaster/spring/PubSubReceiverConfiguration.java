package com.timoxino.interview.toastmaster.spring;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import com.timoxino.interview.toastmaster.annotation.GcpCloudRun;

@Configuration
@GcpCloudRun
public class PubSubReceiverConfiguration {

  private final static Logger LOGGER = LoggerFactory.getLogger(PubSubReceiverConfiguration.class);

  @Bean
  public Consumer<Message<String>> receiveMessageFromTopicTwo() {
    return message -> {
      LOGGER.info(
          "Message arrived via an input binder from cv_pending_topic! Payload: " + message.getPayload());
    };
  }
}