package com.dvivas.card.utils;

import com.dvivas.card.dto.CardDto;
import com.dvivas.card.model.Card;
import org.springframework.beans.BeanUtils;

public final class CardUtil {
    private CardUtil() {
    }

    public static CardDto entityToDto(final Card card) {
        var cardDto = new CardDto();
        BeanUtils.copyProperties(card, cardDto);
        return cardDto;
    }
    public static Card dtoToEntity(final CardDto cardDto) {
        var entity = new Card();
        BeanUtils.copyProperties(cardDto, entity);
        return entity;
    }


}
