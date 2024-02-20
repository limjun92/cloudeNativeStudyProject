package com.mytest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytest.coinInfo.dto.CoinDashboardDbDto;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

//    @KafkaListener(topics = "myTopic", groupId = "myGroup")
//    public void listen(String message) {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            CoinDashboardDbDto dto = mapper.readValue(message, CoinDashboardDbDto.class);
//            System.out.println("Received Message: " + dto.getBuyCnt());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
