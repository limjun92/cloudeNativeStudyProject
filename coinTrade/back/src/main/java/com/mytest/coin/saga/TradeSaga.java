package com.mytest.coin.saga;

import java.util.UUID;

import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import com.mytest.coin.command.AddBalanceCommand;
import com.mytest.coin.command.AddHistCommand;
import com.mytest.coin.command.MinusBalanceCommand;
import com.mytest.coin.event.BalanceAddEvent;
import com.mytest.coin.event.BuyCreatedEvent;
import com.mytest.coin.event.SellCreatedEvent;


@Saga
public class TradeSaga {
	
	@Autowired
	private transient CommandGateway commandGateway;
	
	@StartSaga
	@SagaEventHandler(associationProperty = "tradeId")
	public void handle(BuyCreatedEvent event) {
		System.out.println("saga!!!!!??" + event.getTradeId());

		String walletId = UUID.randomUUID().toString();
		
		//walletId값 연관
		SagaLifecycle.associateWith("walletId", walletId);
		
        commandGateway.send(new AddBalanceCommand(walletId, event.getName(),  (double)event.getPaid()/70000000), new CommandCallback<AddBalanceCommand, Object>() {
            @Override
            public void onResult(CommandMessage<? extends AddBalanceCommand> commandMessage, CommandResultMessage<?> commandResultMessage) {
                if(commandResultMessage.isExceptional()){
                    // 보상 transaction
                    System.out.println("??????????????????");
                    //commandGateway.send(new CancelOrderCommand(event.getOrderId()));
                }
            }
        });
    }
	
	@SagaEventHandler(associationProperty = "walletId")
	public void handle(BalanceAddEvent event) {
		System.out.println(event.getBalance());
		System.out.println("walletId??");
		
		String histId = UUID.randomUUID().toString();
		
		commandGateway.send(new AddHistCommand(histId, event.getName(),  event.getBalance()), new CommandCallback<AddHistCommand, Object>() {
            @Override
            public void onResult(CommandMessage<? extends AddHistCommand> commandMessage, CommandResultMessage<?> commandResultMessage) {
                if(commandResultMessage.isExceptional()){
                    // 보상 transaction
                    System.out.println("??????????????????");
                    //commandGateway.send(new CancelOrderCommand(event.getOrderId()));
                }
            }
        });
	}
	
	@StartSaga
    @SagaEventHandler(associationProperty = "tradeId")
	public void createdSell(SellCreatedEvent event) {
		System.out.println("saga!!!!!??");

        commandGateway.send(new MinusBalanceCommand(event.getTradeId(), event.getName(), event.getVolume()), new CommandCallback<MinusBalanceCommand, Object>() {
            @Override
            public void onResult(CommandMessage<? extends MinusBalanceCommand> commandMessage, CommandResultMessage<?> commandResultMessage) {
                if(commandResultMessage.isExceptional()){
                    // 보상 transaction
                    System.out.println("??????????????????");
                    //commandGateway.send(new CancelOrderCommand(event.getOrderId()));
                }
            }
        });
    }
}
