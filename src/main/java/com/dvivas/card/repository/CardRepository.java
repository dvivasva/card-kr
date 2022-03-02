package com.dvivas.card.repository;

import com.dvivas.card.dto.CardDto;
import com.dvivas.card.model.Card;
import com.dvivas.card.utils.CardUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CardRepository {

    private static final Logger logger = LoggerFactory.getLogger(CardRepository.class);
    private static final String KEY = "Card";
    private final ReactiveRedisOperations<String, Card> redisOperations;
    private final ReactiveHashOperations<String, String, Card> hashOperations;

    @Autowired
    public CardRepository(ReactiveRedisOperations<String, Card> redisOperations) {
        this.redisOperations = redisOperations;
        this.hashOperations = redisOperations.opsForHash();
    }

    public Mono<CardDto> create(Card card) {
        logger.info("inside methode create");
        if (card.getId() != null) {
            String id = UUID.randomUUID().toString();
            card.setId(id);
        }
        return hashOperations.put(KEY, card.getId(), card)
                .map(isSaved -> card).map(CardUtil::entityToDto);
    }

    public Flux<CardDto> read() {
        return hashOperations.values(KEY).map(CardUtil::entityToDto);
    }

    public Mono<CardDto> update(Card card, String id) {
        Mono<Boolean> booleanMono = existsById(id);
        return booleanMono.flatMap(exist -> {
                    if (Boolean.TRUE.equals(exist)) {
                        return hashOperations.put(KEY, card.getId(), card)
                                .map(isSaved -> card);
                       /* return Mono.error(new DuplicateKeyException("Duplicate key, numberCard: " +
                                card.getNumberCard() + " or numberPhone: " + card.getNumberPhone() + " exists."));*/
                    } else {
                        return hashOperations.put(KEY, card.getId(), card)
                                .map(isSaved -> card);
                    }
                })
                .thenReturn(card).map(CardUtil::entityToDto);
    }

    public Mono<Void> delete(String id) {
        return hashOperations.remove(KEY, id).then();
    }

    public Mono<CardDto> findByNumberCard(String numberCard) {
        return hashOperations.values(KEY)
                .filter(p -> p.getNumberCard().equals(numberCard))
                .singleOrEmpty().map(CardUtil::entityToDto);
    }

    public Mono<CardDto> findById(String id) {
        return hashOperations.get(KEY, id).map(CardUtil::entityToDto);
    }

    public Mono<Boolean> existsById(String id) {
        return hashOperations.hasKey(KEY, id);
    }


}
