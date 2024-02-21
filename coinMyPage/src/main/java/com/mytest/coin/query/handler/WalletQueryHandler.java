package com.mytest.coin.query.handler;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mytest.coin.entity.WalletEntity;
import com.mytest.coin.query.GetWalletQuery;
import com.mytest.coin.repository.BalanceRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WalletQueryHandler {
	
	private final BalanceRepository BalanceRepository;
	
	@QueryHandler
	protected List<WalletEntity> on(GetWalletQuery query){
		System.out.println("?????");
		return BalanceRepository.findAll();
	}
}
