package com.dvivas.card.service;

import com.dvivas.card.utils.Topic;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@RequiredArgsConstructor
@Service
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendNumberAccountOrigin(String value) {
        kafkaTemplate.send(Topic.FIND_ACCOUNT_ORIGIN,value);
        logger.info("Messages successfully pushed on topic: " + Topic.FIND_ACCOUNT_ORIGIN);
    }
    public void sendNumberAccountDestination(String value) {
        kafkaTemplate.send(Topic.FIND_ACCOUNT_DESTINATION,value);
        logger.info("Messages successfully pushed on topic: " + Topic.FIND_ACCOUNT_DESTINATION);
    }
    /*
    public void sendMessageNumberAccount(String value) {

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(Topic.FIND_ACCOUNT, value);
        future.addCallback(new ListenableFutureCallback() {
            @Override
            public void onFailure(Throwable ex) {
                logger.info("Messages failed to push on topic: " + Topic.FIND_ACCOUNT);
            }

            @Override
            public void onSuccess(Object result) {
                logger.info("Messages successfully pushed on topic: " + Topic.FIND_ACCOUNT);
            }
        });
    }*/
}
