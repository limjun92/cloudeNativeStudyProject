package com.mytest.coinInfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mytest.coinInfo.dto.CoinDashboardDbListDto;
import com.mytest.coinInfo.dto.CoinDto;

@Mapper
@Repository
public interface CoinInfoMapper {
	CoinDashboardDbListDto CoinDashBoardDB(Map<String, String> params);

	List<CoinDto> getCoin();
}
