package com.mytest.coinInfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mytest.coinInfo.dto.Request;
import com.mytest.coinInfo.dto.StringDto;
import com.mytest.coinInfo.service.CoinTradeSchedulerService;
import com.mytest.core.MessageUtil;
import com.mytest.core.ResponseMessage;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CoinTradeSchedulerController {

	@Autowired
	private final CoinTradeSchedulerService coinTradeSchedulerService;
	
	@PostMapping("/CoinDirectBuySellStart")
    public ResponseMessage CoinDirectBuySellStart(@RequestBody Request upsertParams) {
		System.out.println("??");
		coinTradeSchedulerService.CoinDirectBuySellStart();
        
        StringDto coinStart = new StringDto("Started");
        ResponseMessage response = MessageUtil.getInformationResponseMessage("outPut", coinStart);
        return response;
    }
	
	@PostMapping("/CoinDirectBuySellStop")
    public ResponseMessage CoinBuySellStop() {
		System.out.println("Controller Stop");
		coinTradeSchedulerService.CoinDirectBuySellStop();
        
        StringDto coinStopped = new StringDto("Stopped");
        ResponseMessage response = MessageUtil.getInformationResponseMessage("outPut", coinStopped);
        return response;
    }
}
