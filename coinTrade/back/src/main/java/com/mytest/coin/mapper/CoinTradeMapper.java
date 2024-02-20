package com.mytest.coin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mytest.coin.dto.CoinTradeDto;

@Mapper
@Repository
public interface CoinTradeMapper {
	List<CoinTradeDto> getCoinTrade();
}
