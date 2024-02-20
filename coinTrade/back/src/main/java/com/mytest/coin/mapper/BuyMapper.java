package com.mytest.coin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mytest.coin.entity.BuyEntity;

@Mapper
@Repository
public interface BuyMapper {
	void buySave(BuyEntity buyEntity);
}
