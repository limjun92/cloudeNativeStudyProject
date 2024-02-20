package com.mytest.service;


import org.apache.kafka.common.serialization.Serializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.mytest.coinInfo.dto.CoinDashboardDbDto;

public class objectSerializer implements Serializer<CoinDashboardDbDto>{
	@Override
    public byte[] serialize(String topic, CoinDashboardDbDto data) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }
}
