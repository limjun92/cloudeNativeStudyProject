package com.mytest.coin.handler;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PolicyHandler {
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
