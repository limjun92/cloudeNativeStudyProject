package com.mytest.coin.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.springframework.stereotype.Service;

import org.axonframework.queryhandling.QueryGateway;

import com.mytest.coin.dto.Request;
import com.mytest.coin.entity.WalletEntity;
import com.mytest.coin.query.GetWalletQuery;

@Service
public class WelletService {
	
	private final QueryGateway queryGateway;
	
	WelletService(QueryGateway queryGateway){
		this.queryGateway = queryGateway;
	}

	public List<WalletEntity> getBalance(Request upsertParams) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		System.out.println("service!!");
		return queryGateway.query(new GetWalletQuery(), ResponseTypes.multipleInstancesOf(WalletEntity.class)).get();
	}
}
