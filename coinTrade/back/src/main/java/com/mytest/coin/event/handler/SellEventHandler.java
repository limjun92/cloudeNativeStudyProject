package com.mytest.coin.event.handler;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.mytest.coin.entity.BuyEntity;
import com.mytest.coin.entity.SellEntity;
import com.mytest.coin.event.BuyCreatedEvent;
import com.mytest.coin.event.SellCreatedEvent;
import com.mytest.coin.repository.BuyRepository;
import com.mytest.coin.repository.SellRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SellEventHandler {

	private final SellRepository sellRepository;
	
	@EventHandler
	protected void on(SellCreatedEvent sellCreatedEvent) {
		SellEntity sellEntity = new SellEntity();
		sellEntity.setTradeid(sellCreatedEvent.getTradeId());
		sellEntity.setName(sellCreatedEvent.getName());
		sellEntity.setVolume(sellCreatedEvent.getVolume());
		
		sellRepository.save(sellEntity);
	}
	
}
