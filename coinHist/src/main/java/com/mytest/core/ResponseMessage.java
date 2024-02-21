package com.mytest.core;

import java.util.Map;

import lombok.Data;

@Data
public class ResponseMessage extends BaseDto{
	
	private Map<String, BaseDto> serviceResponse;
}
