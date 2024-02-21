package com.mytest.coin.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BalanceMinusEvent {
    private final String walletId;
    private final String name;
    private final double balance;
}
