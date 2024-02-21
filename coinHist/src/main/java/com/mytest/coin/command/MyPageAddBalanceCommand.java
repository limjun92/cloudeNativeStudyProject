package com.mytest.coin.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MyPageAddBalanceCommand {
	
	@TargetAggregateIdentifier
    private String tradeid;
    private String name;
    private double balance;
}
