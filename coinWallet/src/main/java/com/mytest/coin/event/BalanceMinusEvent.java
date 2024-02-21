package com.mytest.coin.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BalanceMinusEvent {
    private String walletId;
    private String name;
    private double balance;
}
