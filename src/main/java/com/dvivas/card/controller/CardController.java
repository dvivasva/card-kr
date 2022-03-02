package com.dvivas.card.controller;

import com.dvivas.card.dto.CardDto;
import com.dvivas.card.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
public class CardController {


    private  final CardService cardService;

    @GetMapping("/{numberCard}")
    @Cacheable(value = "Card", key = "numberCard")
    public Mono<CardDto> findByNumberCard(@PathVariable final String numberCard) {
        return cardService.findByNumberCard(numberCard);
    }

}
