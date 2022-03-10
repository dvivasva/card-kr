package com.dvivas.card.message;

import com.dvivas.card.dto.CardDto;
import com.dvivas.card.model.Payment;
import com.dvivas.card.service.CardService;
import com.dvivas.card.utils.JsonUtils;
import com.dvivas.card.utils.Topic;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class Receive {

    private static final Logger logger = LoggerFactory.getLogger(Receive.class);
    private final CardService cardService;
    private final Sender sender;

    @KafkaListener(topics = Topic.FIND_NUMBERS_CARDS, groupId = "group_id_card")
    public void consumeFromWallet(String param) {
        logger.info("Has been published an insert payment from service wallet-kr : " + param);
        sendMessageToAccount(param);
    }
    public void sendMessageToAccount(String param) {
        Payment payment;
        try {
            payment = JsonUtils.convertFromJsonToObject(param, Payment.class);
            Mono<CardDto> cardOrigin = cardService.findByNumberCard(payment.getNumberPhoneOrigin());
            cardOrigin
                    .switchIfEmpty(Mono.error(new ClassNotFoundException("not exist card")))
                    .doOnNext(p -> {
                        payment.setNumberPhoneOrigin(p.getNumberAccount());

                        Mono<CardDto> cardDestination = cardService.findByNumberCard(payment.getNumberPhoneDestination());
                        cardDestination.switchIfEmpty(Mono.error(new ClassNotFoundException("not exist card")))
                                .doOnNext(v -> {
                                    payment.setNumberPhoneDestination(v.getNumberAccount());
                                    sender.sendRequestPaymentToAccount(payment);
                                    logger.info("send messages to account-kr -->");
                                }).subscribe();
                    }).subscribe();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @KafkaListener(topics = Topic.RESPONSE_PAYMENT_ON_CARD, groupId = "group_id_card")
    public void consumeFromAccount(String param) {
        logger.info("Has been published an insert payment from service account-kr : " + param);
        sendMessageToWallet(param);
    }
    public void sendMessageToWallet(String param) {
        Payment payment;
        try {
            payment = JsonUtils.convertFromJsonToObject(param, Payment.class);
            Mono<CardDto> cardOrigin = cardService.findByNumberAccount(payment.getNumberPhoneOrigin());
            cardOrigin
                    .switchIfEmpty(Mono.error(new ClassNotFoundException("not exist card")))
                    .doOnNext(p -> {

                        payment.setNumberPhoneOrigin(p.getNumberCard());

                        Mono<CardDto> cardDestination = cardService.findByNumberAccount(payment.getNumberPhoneDestination());
                        cardDestination.switchIfEmpty(Mono.error(new ClassNotFoundException("not exist card")))
                                .doOnNext(v -> {
                                    payment.setNumberPhoneDestination(v.getNumberCard());
                                    sender.sendResponsePaymentToWallet(payment);
                                    logger.info("send messages to wallet-kr -->");
                                }).subscribe();
                    }).subscribe();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
