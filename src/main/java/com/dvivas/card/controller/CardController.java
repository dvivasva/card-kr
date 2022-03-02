package com.dvivas.card.controller;

import com.dvivas.card.dto.CardDto;
import com.dvivas.card.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
public class CardController {


    private  final CardService cardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CardDto> create(
            @RequestBody final Mono<CardDto> cardDtoMono) {
        return cardService.create(cardDtoMono);
    }

    @GetMapping("/{numberCard}")
    @Cacheable(value = "Card", key = "numberCard")
    public Mono<CardDto> findByNumberCard(@PathVariable final String numberCard) {
        return cardService.findByNumberCard(numberCard);
    }

}
