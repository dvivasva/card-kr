package com.dvivas.card.listener;


import com.dvivas.card.component.CardComponent;
import com.dvivas.card.service.KafkaProducer;
import com.dvivas.card.utils.JsonUtils;
import com.dvivas.card.utils.Topic;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    private final CardComponent cardComponent;
    private final KafkaProducer kafkaProducer;

    @KafkaListener(topics = Topic.FIND_NUMBER_CARD_ORIGIN, groupId = "group_id")
    public void consumeCardOrigin(String param) {
        logger.info("Has been published find number card origin from service wallet-kr : " + param);
        sendMessageAccount(param, 0);

    }

    @KafkaListener(topics = Topic.FIND_NUMBER_CARD_DESTINATION, groupId = "group_id")
    public void consumeCardDestination(String param) {
        logger.info("Has been published find number card destination from service wallet-kr : " + param);
        sendMessageAccount(param, 1);

    }
    public void sendMessageAccount(String param, int index) {
        String newNumberCard = JsonUtils.removeFirstAndLast(param);
        var find = cardComponent.findByNumberCard(newNumberCard);
        find.doOnNext(p -> {
            if (index == 0) {
                kafkaProducer.sendNumberAccountOrigin(p.getNumberAccount());
            } else {
                kafkaProducer.sendNumberAccountDestination(p.getNumberAccount());
            }
            logger.info("send messages to account -->");
        }).subscribe();
    }



   /* @KafkaListener(topics = Topic.FIND_ACCOUNT, groupId = "group_id")
    public void consume(String param) {
        logger.info("Has been published find account from service wallet-kr : " + param);
         sendMessageAccount(param);
    }
    public void sendMessageAccount(String param){
        String newNumberCard= JsonUtils.removeFirstAndLast(param);
       var find= cardComponent.findByNumberCard(newNumberCard);
        find.doOnNext(p->{
                     kafkaProducer.sendMessageNumberAccount(p.getNumberAccount());
                    logger.info("send message to account"+p);
                })
                .subscribe();
    }*/

}
