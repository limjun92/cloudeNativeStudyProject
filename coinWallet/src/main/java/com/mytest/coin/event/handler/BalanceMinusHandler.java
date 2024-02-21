package com.mytest.coin.event.handler;

import java.util.UUID;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.mytest.coin.command.CancelSellCommand;
import com.mytest.coin.entity.WalletEntity;
import com.mytest.coin.event.BalanceAddEvent;
import com.mytest.coin.event.BalanceMinusEvent;
import com.mytest.coin.repository.BalanceRepository;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Component
@AllArgsConstructor
@Slf4j
public class BalanceMinusHandler {

	@Autowired
    private transient CommandGateway commandGateway;
	
	private final BalanceRepository balanceRepository;
	
	@EventHandler
	protected void on(BalanceMinusEvent balanceMinusEvent) {
	    String name = balanceMinusEvent.getName();
	    System.out.println("==================================");
	    System.out.println(balanceMinusEvent.getWalletId() + " " +  balanceMinusEvent.getName()+ " " +  balanceMinusEvent.getBalance());
	    
	    WalletEntity existingWallet = balanceRepository.findByName(name);
	    
	    if (existingWallet == null || existingWallet.getBalance() < balanceMinusEvent.getBalance()) {
	    	log.info("[보상Transaction] cancel order");
            commandGateway.send(new CancelSellCommand(existingWallet.getWalletId()));
	    } else {
	    	System.out.println("existingWallet" + existingWallet.getName() + " " + existingWallet.getBalance());
	        // 이미 있는 데이터인 경우
	        Double currentBalance = existingWallet.getBalance();
	        Double newBalance = currentBalance - balanceMinusEvent.getBalance();
	        existingWallet.setBalance(newBalance);
	        System.out.println("newBalance " + newBalance + " = " + currentBalance + " - " + balanceMinusEvent.getBalance());
	    }

	    System.out.println("save 완료");
	    System.out.println(balanceRepository.findAll());
	}
	
}
