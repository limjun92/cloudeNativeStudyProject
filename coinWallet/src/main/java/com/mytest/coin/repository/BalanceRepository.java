package com.mytest.coin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytest.coin.entity.WalletEntity;


public interface BalanceRepository extends JpaRepository<WalletEntity, String>{
	WalletEntity findByName(String name);
}
