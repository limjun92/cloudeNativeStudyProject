package com.mytest.coin.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.amazonaws.Request;
import com.mytest.coin.service.WelletService;


public class WalletController {
	@Autowired
	WelletService welletService;
	
    @PostMapping("/")
    public String buy(@RequestBody Request upsertParams) throws IOException {
    	System.out.println("Controller");
    	return welletService.buy(upsertParams);
    }
}
