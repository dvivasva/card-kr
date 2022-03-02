package com.dvivas.card.component;

import com.dvivas.card.dto.CardDto;
import com.dvivas.card.model.Card;
import com.dvivas.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class CardComponent {
    private final CardRepository cardRepository;

    public Mono<CardDto> create(Card card) {
        return cardRepository.create(card);
    }

    public Flux<CardDto> read() {
        return cardRepository.read();
    }

    public Mono<CardDto> update(Card card, String id) {
        return cardRepository.update(card, id);
    }

    public Mono<Void> delete(String id) {
        return cardRepository.delete(id);
    }

    public Mono<CardDto> findById(String id) {
        return cardRepository.findById(id);
    }

    public Mono<CardDto> findByNumberCard(String numberCard) {
        return cardRepository.findByNumberCard(numberCard);
    }
}
