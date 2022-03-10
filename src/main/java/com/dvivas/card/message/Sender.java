package com.dvivas.card.message;

import com.dvivas.card.model.Payment;
import com.dvivas.card.utils.Topic;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class Sender {

    private static final Logger logger = LoggerFactory.getLogger(Sender.class);
    private final KafkaTemplate<String, Payment> requestPaymentKafkaTemplate;

    public void sendRequestPaymentToAccount(Payment payment) {
        requestPaymentKafkaTemplate.send(Topic.FIND_NUMBERS_ACCOUNTS,payment);
        logger.info("Messages successfully pushed on topic: " + Topic.FIND_NUMBERS_ACCOUNTS);
    }

    public void sendResponsePaymentToWallet(Payment payment) {
        requestPaymentKafkaTemplate.send(Topic.RESPONSE_PAYMENT_ON_WALLET,payment);
        logger.info("Messages successfully pushed on topic: " + Topic.RESPONSE_PAYMENT_ON_WALLET);
    }
}
