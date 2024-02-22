package com.mytest.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytest.coin.dto.CoinDto;
import com.mytest.coin.dto.CoinHist;
import com.mytest.coin.dto.CoinTradeDto;
import com.mytest.coin.mapper.CoinTradeMapper;
import com.mytest.coin.util.CoinAPI;

@Service
public class KafkaConsumer {

	@Autowired
	private CoinTradeMapper coinTradeMapper;

	@Autowired
	private KafkaProducer kafkaProducer;

	private CoinAPI coinApi;

	public KafkaConsumer() {
		coinApi = new CoinAPI();
	}

//	@KafkaListener(topics = "coinInfo", groupId = "myGroup")
//	public void listen(String message) {
//		System.out.println("??");
//		try {
//			ObjectMapper mapper = new ObjectMapper();
//			List<CoinDto> coins = mapper.readValue(message, new TypeReference<List<CoinDto>>() {});
//			System.out.println("Received" + coins.toString());
//
//			String coinsNm = coins.get(0).getMarket();
//			for (int i = 1; i < coins.size(); i++) {
//				coinsNm += ", " + coins.get(i).getMarket();
//			}
//
//			JSONArray CurrentPrice = coinApi.CurrentCoinPrice(coinsNm);
//
////          System.out.println(CurrentPrice);
//			List<CoinTradeDto> coinTrade = coinTradeMapper.getCoinTrade();
//
//			for (int i = 0; i < coinTrade.size(); i++) {
//				JSONObject jsonObj = (JSONObject) CurrentPrice.get(i);
//
//				String jsonMarket = jsonObj.getString("market");
//
//				Double currentPrice = Double.parseDouble((String.valueOf(jsonObj.get("trade_price"))));
//
//				System.out.println(jsonMarket);
//				System.out.println("?? " + coinTrade.get(i).getMarket());
//				System.out.println();
//
//				Double fixPrie = coinTrade.get(i).getFixPrice();
//
//				if (currentPrice < fixPrie) {
//					
//					CoinHist coinHist = new CoinHist();
//					
//					coinHist.setBuyPrice(10000);
//					coinHist.setBuyWonPay(5000);
//					coinHist.setMarket("KRW-BTC");
//					
//					System.out.println("/////123123");
//					try {
//						ObjectMapper producerMapper = new ObjectMapper();
//						String msg = producerMapper.writeValueAsString(coinHist);
//						kafkaProducer.sendMessage(msg);
//					} catch (JsonProcessingException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
