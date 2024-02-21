package com.mytest.coin.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddBalanceCommand {
	
	@TargetAggregateIdentifier
	private String tradeId;
    private String name;
    private double balance;
}
