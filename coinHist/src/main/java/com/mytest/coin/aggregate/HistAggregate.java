package com.mytest.coin.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.mytest.coin.command.AddHistCommand;

import lombok.NoArgsConstructor;

@Aggregate
@NoArgsConstructor
public class HistAggregate {

	@AggregateIdentifier
    private String histId;  // 식별자 필드를 추가했습니다.
	private String name;
	private Double balance;
	
	@CommandHandler
	public HistAggregate(AddHistCommand command) {
		System.out.println("AddHistCommand " + command.getHistId());
	}
}
