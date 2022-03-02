package com.dvivas.card.utils;

import com.dvivas.card.component.CardComponent;
import com.dvivas.card.model.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DefaultLoad implements CommandLineRunner {

    private final CardComponent cardComponent;

    @Override
    public void run(String... args) throws Exception {
        var cardOne = new Card("CARD-001", "4214-1001", "018-000-101");
        cardComponent.create(cardOne);
        var cardTwo = new Card("CARD-002", "4214-1002", "018-000-102");
        cardComponent.create(cardTwo);
        var cardThree = new Card("CARD-003", "4214-1003", "018-000-103");
        cardComponent.create(cardThree);
        var cardFour = new Card("CARD-004", "4214-1004", "018-000-104");
        cardComponent.create(cardFour);
        var cardFive = new Card("CARD-005", "4214-1005", "018-000-105");
        cardComponent.create(cardFive);
        var cardSix = new Card("CARD-006", "4214-1006", "018-000-106");
        cardComponent.create(cardSix);
        var cardSeven = new Card("CARD-007", "4214-1007", "018-000-107");
        cardComponent.create(cardSeven);
        var cardEight = new Card("CARD-008", "4214-1008", "018-000-108");
        cardComponent.create(cardEight);
        var cardNine = new Card("CARD-009", "4214-1009", "018-000-109");
        cardComponent.create(cardNine);
        var cardTen = new Card("CARD-010", "4214-1010", "018-000-110");
        cardComponent.create(cardTen);
    }
}
