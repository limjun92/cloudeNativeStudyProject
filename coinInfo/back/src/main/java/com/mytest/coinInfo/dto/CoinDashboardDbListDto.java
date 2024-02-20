package com.mytest.coinInfo.dto;

import java.util.List;

import com.mytest.core.BaseDto;

import lombok.Data;

@Data
public class CoinDashboardDbListDto extends BaseDto{
	private int coinDashboardCount;
	private List<CoinDashboardDbDto> coinDashboardDbDto;
}
