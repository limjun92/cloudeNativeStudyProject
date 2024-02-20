package com.mytest.coinInfo.service;

import java.sql.Timestamp;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytest.coinInfo.dto.CoinDashboardDbDto;
import com.mytest.coinInfo.dto.CoinDashboardDbListDto;
import com.mytest.coinInfo.dto.Request;
import com.mytest.coinInfo.mapper.CoinInfoMapper;
import com.mytest.core.MessageUtil;
import com.mytest.core.ResponseMessage;
import com.mytest.service.KafkaProducer;



@Service
public class CoinMainService {

	private String serverUrl = "https://api.upbit.com";
	private String accessKey = "";
	private String secretKey = "";
	
	@Autowired
	private CoinInfoMapper coinMapper;
	
	@Autowired
	private KafkaProducer kafkaProducer;
	/**
	 * 
	 * @param searchParams
	 * @return
	 */
	//DB 코인 대시보드 조회
	public ResponseMessage CoinDashBoardDB(Request searchParams) {
		System.out.println("Service");
		
		CoinDashboardDbDto coinDashboardDbDto = new CoinDashboardDbDto(1,"1","1",1,1,true,1.2,1.2,1.2,1.2,1.2,1.2,1.2,1.2,1.2,1.2,new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()));
		
		try {
			ObjectMapper mapper = new ObjectMapper();
	        String msg = mapper.writeValueAsString(coinDashboardDbDto);
	        kafkaProducer.sendMessage(msg);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//kafkaProducer.sendMessage("123123");
		
		Map<String, String> params = searchParams.getSearchParams();
		
		CoinDashboardDbListDto coinDashboardDbList = coinMapper.CoinDashBoardDB(params);
		
		ResponseMessage response = MessageUtil.getInformationResponseMessage("outPut", coinDashboardDbList);
		
		return response;
	}
}
