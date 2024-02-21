package com.mytest.coin.event.handler;

import java.util.UUID;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.mytest.coin.entity.WalletEntity;
import com.mytest.coin.event.BalanceAddEvent;
import com.mytest.coin.repository.BalanceRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BalanceAddHandler {

	private final BalanceRepository balanceRepository;
	
	@EventHandler
	protected void on(BalanceAddEvent balanceAddEvent) {
	    String name = balanceAddEvent.getName();
	    System.out.println("==================================");
	    System.out.println(balanceAddEvent.getWalletId() + " " +  balanceAddEvent.getName()+ " " +  balanceAddEvent.getBalance());
	    
	    WalletEntity existingWallet = balanceRepository.findByName(name);
	    
	    if (existingWallet != null) {
	    	System.out.println("existingWallet" + existingWallet.getName() + " " + existingWallet.getBalance());
	        // 이미 있는 데이터인 경우
	        Double currentBalance = existingWallet.getBalance();
	        Double newBalance = currentBalance + balanceAddEvent.getBalance();
	        existingWallet.setBalance(newBalance);
	        System.out.println("newBalance " + newBalance + " = " + currentBalance + " + " + balanceAddEvent.getBalance());
	    } else {
	    	System.out.println("?????????????????????????????????????????????");
	        // 새로운 데이터인 경우
	        WalletEntity newWallet = new WalletEntity();
	        newWallet.setWalletId(UUID.randomUUID().toString());
	        newWallet.setName(name);
	        newWallet.setBalance(balanceAddEvent.getBalance());
	        balanceRepository.save(newWallet);
	    }

	    System.out.println("save 완료");
	    System.out.println(balanceRepository.findAll());
	}
}
