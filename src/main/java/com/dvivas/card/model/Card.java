package com.dvivas.card.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Card")
public class Card  implements Serializable {

    @Id
    private String id;
    private String numberCard;
    private  String numberAccount;

}
