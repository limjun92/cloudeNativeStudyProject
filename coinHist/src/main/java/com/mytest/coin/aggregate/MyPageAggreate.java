package com.mytest.coin.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.mytest.coin.command.MyPageAddBalanceCommand;

import lombok.NoArgsConstructor;

@Aggregate
@NoArgsConstructor
public class MyPageAggreate {
    
	@AggregateIdentifier
    private String id;  // 식별자 필드를 추가했습니다.
	
    @CommandHandler
    public MyPageAggreate(MyPageAddBalanceCommand command) {
    	this.id = command.getTradeid();
    	System.out.println("제발 여기까지 와주세요");
    }
}
