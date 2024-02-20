package com.mytest.coin.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import com.mytest.coin.command.CreateBuyCommand;
import com.mytest.coin.dto.Request;
import com.mytest.coin.entity.BuyEntity;
import com.mytest.coin.query.GetBuyQuery;

@Service
public class TradeService {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public TradeService(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    public String buy(Request upsertParam) {
        // command생성
    	
    	Map<String, String> params = upsertParam.getUpsertParams();
    	    	
        CreateBuyCommand createBuyCommand = new CreateBuyCommand(UUID.randomUUID().toString(), params.get("name"), Integer.parseInt(params.get("paid")));

        // 생성한 command전송(비동기)
        String returnValue = commandGateway.sendAndWait(createBuyCommand);
        System.out.printf("returnValue: %s \n", returnValue);
        
        return returnValue;
    }

	public List<BuyEntity> getBuy(Request searchParams) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		return queryGateway.query(new GetBuyQuery(), ResponseTypes.multipleInstancesOf(BuyEntity.class)).get();
	}
}
