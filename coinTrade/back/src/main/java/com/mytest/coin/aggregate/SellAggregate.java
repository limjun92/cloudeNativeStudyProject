package com.mytest.coin.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.mytest.coin.command.CreateSellCommand;
import com.mytest.coin.event.SellCreatedEvent;

import lombok.NoArgsConstructor;

@Aggregate
@NoArgsConstructor
public class SellAggregate {
	
    @AggregateIdentifier
    private String id;
    private String name;
    private Double volume;

	@CommandHandler
	public SellAggregate(CreateSellCommand command) {
		System.out.println("@CommandHandler");
		apply(new SellCreatedEvent(command.getTradeid(), command.getName(), command.getVolume()));
	}
	
    @EventSourcingHandler
    public void createSell(SellCreatedEvent event) {
    	System.out.println("@EventSourcingHandler");
        this.id = event.getTradeId();
        this.name = event.getName();
        this.volume = event.getVolume();
    }
}
