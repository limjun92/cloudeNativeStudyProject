package com.mytest.coin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytest.coin.entity.BuyEntity;

public interface BuyRepository extends JpaRepository<BuyEntity, String>{

}
