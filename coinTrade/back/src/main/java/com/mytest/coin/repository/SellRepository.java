package com.mytest.coin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytest.coin.entity.SellEntity;

public interface SellRepository extends JpaRepository<SellEntity, String>{

}
