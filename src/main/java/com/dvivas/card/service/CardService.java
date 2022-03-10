package com.dvivas.card.service;

import com.dvivas.card.dto.CardDto;
import com.dvivas.card.model.Card;
import com.dvivas.card.repository.ICardRepository;
import com.dvivas.card.utils.CardUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CardService {

    private final static Logger logger = LoggerFactory.getLogger(CardService.class);
    private final ReactiveMongoTemplate reactiveMongoTemplate;
    private  final ICardRepository iCardRepository;

    public Mono<CardDto> create(final Mono<CardDto> entityToDto) {
        return entityToDto.map(CardUtil::dtoToEntity)
                .flatMap(iCardRepository::save)
                .map(CardUtil::entityToDto);

    }
    public Mono<CardDto> findByNumberCard(String numberCard) {
        logger.info("inside methode find by number Card ");
        Query query = new Query();
        query.addCriteria(Criteria.where("numberCard").is(numberCard));
        return reactiveMongoTemplate.findOne(query, Card.class).map(CardUtil::entityToDto);

    }
    public Mono<CardDto> findByNumberAccount(String number) {
        logger.info("inside methode find by number account ");
        Query query = new Query();
        query.addCriteria(Criteria.where("numberAccount").is(number));
        return reactiveMongoTemplate.findOne(query, Card.class).map(CardUtil::entityToDto);

    }
}
