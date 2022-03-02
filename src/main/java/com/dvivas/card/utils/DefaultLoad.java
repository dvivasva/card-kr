package com.dvivas.card.utils;

import com.dvivas.card.dto.CardDto;
import com.dvivas.card.model.Card;
import com.dvivas.card.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class DefaultLoad implements CommandLineRunner {

    private final CardService cardService;

    @Override
    public void run(String... args) throws Exception {
        var cardOne = new CardDto("CARD-001", "4214-1001", "018-000-101");
        cardService.create(Mono.just(cardOne));
        var cardTwo = new CardDto("CARD-002", "4214-1002", "018-000-102");
        cardService.create(Mono.just(cardTwo));
        var cardThree = new CardDto("CARD-003", "4214-1003", "018-000-103");
        cardService.create(Mono.just(cardThree));
        var cardFour = new CardDto("CARD-004", "4214-1004", "018-000-104");
        cardService.create(Mono.just(cardFour));
        var cardFive = new CardDto("CARD-005", "4214-1005", "018-000-105");
        cardService.create(Mono.just(cardFive));
        var cardSix = new CardDto("CARD-006", "4214-1006", "018-000-106");
        cardService.create(Mono.just(cardSix));
        var cardSeven = new CardDto("CARD-007", "4214-1007", "018-000-107");
        cardService.create(Mono.just(cardSeven));
        var cardEight = new CardDto("CARD-008", "4214-1008", "018-000-108");
        cardService.create(Mono.just(cardEight));
        var cardNine = new CardDto("CARD-009", "4214-1009", "018-000-109");
        cardService.create(Mono.just(cardNine));
        var cardTen = new CardDto("CARD-010", "4214-1010", "018-000-110");
        cardService.create(Mono.just(cardTen));
    }
}
