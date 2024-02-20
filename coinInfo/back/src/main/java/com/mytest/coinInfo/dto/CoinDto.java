package com.mytest.coinInfo.dto;

import lombok.Data;

@Data
public class CoinDto {
	private int rowId;
	private String market;
	
	public CoinDto() {  // 기본 생성자
    }
}
