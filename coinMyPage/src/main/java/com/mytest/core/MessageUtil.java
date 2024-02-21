package com.mytest.core;

import java.util.HashMap;
import java.util.Map;

public class MessageUtil {
	
	public static ResponseMessage getInformationResponseMessage(String dataSetName, BaseDto resultDto) {
		Map<String, BaseDto> serviceResponse = new HashMap<>();
		serviceResponse.put(dataSetName, resultDto);
		ResponseMessage response = new ResponseMessage();
		response.setServiceResponse(serviceResponse);
		return response;
	}
}
