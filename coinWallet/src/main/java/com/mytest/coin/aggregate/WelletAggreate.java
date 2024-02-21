package com.mytest.coin.aggregate;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.mytest.coin.command.AddBalanceCommand;
import com.mytest.coin.command.CancelSellCommand;
import com.mytest.coin.command.MinusBalanceCommand;
import com.mytest.coin.entity.WalletEntity;
import com.mytest.coin.event.BalanceAddEvent;
import com.mytest.coin.event.BalanceMinusEvent;

import lombok.NoArgsConstructor;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
public class WelletAggreate {
    
	@AggregateIdentifier
    private String walletId;  // 식별자 필드를 추가했습니다.
	private String name;
	private Double balance;
	
    @CommandHandler
    public WelletAggreate(AddBalanceCommand command) {
    	this.name = command.getName();
    	this.balance = command.getBalance();
    	System.out.println(command.getWalletId() + " " + command.getBalance() + " " +  command.getName());
    	this.walletId = UUID.randomUUID().toString();
    	System.out.println("제발 여기까지 와주세요" + command.getWalletId());
    	
    	apply(new BalanceAddEvent(command.getWalletId(), command.getName(), command.getBalance()));
    }
    
//    @EventSourcingHandler
//    public void AddBalance(BalanceAddEvent event) {
//    	System.out.println("asdEventSourcingHandler");
//    	this.name = event.getName();
//    	this.balance = event.getBalance();
//    	this.tradeId = event.getTradeId();
//    }
    
    @CommandHandler
    public WelletAggreate(MinusBalanceCommand command) {
    	System.out.println("Minus제발 여기까지 와주세요");
    	apply(new BalanceMinusEvent(this.walletId, command.getName(), command.getBalance()));
    }
    
    @EventSourcingHandler
    public void MinusBalance(BalanceMinusEvent event) {
    	System.out.println("EventSourcingHandler");
    	this.walletId = UUID.randomUUID().toString();
    	this.name = event.getName();
    	this.balance = event.getBalance();
    }
    
    @CommandHandler
    public WelletAggreate(CancelSellCommand command) {
    	System.out.println("CancelSellCommand");
    }
}
