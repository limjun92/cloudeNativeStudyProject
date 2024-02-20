package com.mytest.coin.query.handler;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.mytest.coin.entity.BuyEntity;
import com.mytest.coin.query.GetBuyQuery;
import com.mytest.coin.repository.BuyRepositoey;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BuyQueryHandler {
	private final BuyRepositoey buyRepositoey;
	
	@QueryHandler
    protected List<BuyEntity> on(GetBuyQuery query) {
        return buyRepositoey.findAll();
    }
}
