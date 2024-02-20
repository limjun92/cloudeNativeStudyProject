package com.mytest.coinInfo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytest.coinInfo.dto.Request;
import com.mytest.coinInfo.service.CoinMainService;
import com.mytest.core.ResponseMessage;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CoinMainController {
	
	@Autowired
	private CoinMainService coinService;
	
    //DB 코인 조회
    @PostMapping("/CoinDashBoardDB")
    public ResponseMessage CoinDashBoardDB(@RequestBody Request searchParams) throws IOException {
    	System.out.println("Controller");
    	return coinService.CoinDashBoardDB(searchParams);
    }
    
}
