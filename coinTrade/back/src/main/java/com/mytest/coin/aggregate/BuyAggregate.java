package com.mytest.coin.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.mytest.coin.command.CreateBuyCommand;
import com.mytest.coin.command.CreateSellCommand;
import com.mytest.coin.event.BuyCreatedEvent;

import lombok.NoArgsConstructor;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
public class BuyAggregate {
	
    @AggregateIdentifier
    private String id;
    private String name;
    private Integer paid;

	@CommandHandler
	public BuyAggregate(CreateBuyCommand command) {
		System.out.println("@CommandHandler");
		apply(new BuyCreatedEvent(command.getTradeid(), command.getName(), command.getPaid()));
	}
	
    @EventSourcingHandler
    public void createBuy(BuyCreatedEvent event) {
    	System.out.println("@EventSourcingHandler");
        this.id = event.getTradeId();
        this.name = event.getName();
        this.paid = event.getPaid();
    }

}
