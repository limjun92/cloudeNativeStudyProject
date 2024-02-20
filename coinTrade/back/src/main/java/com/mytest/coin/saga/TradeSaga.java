package com.mytest.coin.saga;

import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import com.mytest.coin.command.AddBalanceCommand;
import com.mytest.coin.event.BuyCreatedEvent;


@Saga
public class TradeSaga {
	
	@Autowired
	private transient CommandGateway commandGateway;
	
	@StartSaga
    @SagaEventHandler(associationProperty = "tradeId")
	public void createdBuy(BuyCreatedEvent event) {
		System.out.println("saga!!!!!??");

        commandGateway.send(new AddBalanceCommand(event.getTradeId(), event.getName(), event.getPaid()), new CommandCallback<AddBalanceCommand, Object>() {
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
}
