package com.dvivas.card.controller;

import com.dvivas.card.model.Account;
import com.dvivas.card.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
public class CardController {


    private static final Logger logger = LoggerFactory.getLogger(CardController.class);
    private final KafkaProducer kafkaProducer;
    private final StreamBridge streamBridge;


    @GetMapping("/{numberAccount}")
    public void send(@PathVariable String numberAccount) {
        kafkaProducer.sendMessage(numberAccount);

    }

    @PostMapping
    public void send(@RequestBody Account account) {
        kafkaProducer.sendMessage(account);
    }


    @RequestMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delegateToSupplier(@RequestBody String body) {
        logger.info("Sending" + body);
        streamBridge.send("toStream-out-0", body);
    }

}
