package com.dvivas.card.repository;

import com.dvivas.card.model.Card;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICardRepository extends ReactiveMongoRepository<Card, String> {

}
