package com.mytest.coin.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CancelSellCommand {
	
	@TargetAggregateIdentifier
	private String tradeId;
}
