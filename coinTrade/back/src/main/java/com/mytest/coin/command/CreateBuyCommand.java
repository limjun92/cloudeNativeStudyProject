package com.mytest.coin.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class CreateBuyCommand {

	@TargetAggregateIdentifier
    private final String tradeid;
    private final String name;
    private final Integer paid;
}
