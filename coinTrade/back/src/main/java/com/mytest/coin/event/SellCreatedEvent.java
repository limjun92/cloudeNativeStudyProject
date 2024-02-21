package com.mytest.coin.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SellCreatedEvent {
    private final String tradeId;
    private final String name;
    private final Double volume;
}
