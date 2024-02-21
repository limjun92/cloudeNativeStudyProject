package com.mytest.coin.event.handler;

import lombok.AllArgsConstructor;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mytest.coin.entity.BuyEntity;
import com.mytest.coin.event.BuyCreatedEvent;
import com.mytest.coin.mapper.BuyMapper;
import com.mytest.coin.repository.BuyRepository;

@Component
@AllArgsConstructor
public class BuyEventHandler {
	
	private final BuyRepository buyRepository;
	
	@EventHandler
	protected void on(BuyCreatedEvent buyCreatedEvent) {
		BuyEntity buyEntity = new BuyEntity();
		buyEntity.setTradeid(buyCreatedEvent.getTradeId());
		buyEntity.setName(buyCreatedEvent.getName());
		buyEntity.setPaid(buyCreatedEvent.getPaid());
		
		buyRepository.save(buyEntity);
	}
}
