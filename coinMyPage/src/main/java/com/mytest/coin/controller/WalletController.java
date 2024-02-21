package com.mytest.coin.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mytest.coin.dto.Request;
import com.mytest.coin.entity.WalletEntity;
import com.mytest.coin.service.WelletService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WalletController {
	@Autowired
	WelletService welletService;
	
    @PostMapping("/getBalance")
    public List<WalletEntity> getBalance(@RequestBody Request upsertParams) throws IOException, InterruptedException, ExecutionException {
    	System.out.println("Controller");
    	return welletService.getBalance(upsertParams);
    }
}
