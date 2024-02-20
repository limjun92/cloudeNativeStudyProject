package com.mytest.coin.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mytest.coin.dto.Request;
import com.mytest.coin.entity.BuyEntity;
import com.mytest.coin.service.TradeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TradeController {
	
	@Autowired
	TradeService tradeService;
	
    @PostMapping("/buy")
    public String buy(@RequestBody Request upsertParams) throws IOException {
    	System.out.println("Controller");
    	return tradeService.buy(upsertParams);
    }
    
    @PostMapping("/getBuy")
    public List<BuyEntity> getBuy(@RequestBody Request searchParams) throws IOException, InterruptedException, ExecutionException {
    	System.out.println("Controller");
    	return tradeService.getBuy(searchParams);
    }
}
