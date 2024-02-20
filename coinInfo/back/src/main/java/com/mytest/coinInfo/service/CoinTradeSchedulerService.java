package com.mytest.coinInfo.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytest.coinInfo.dto.CoinDto;
import com.mytest.coinInfo.mapper.CoinInfoMapper;
import com.mytest.service.KafkaProducer;

import jakarta.transaction.Transactional;

@Service
public class CoinTradeSchedulerService {
	
	private final ThreadPoolTaskScheduler taskScheduler;
	private ScheduledFuture<?> job;
	
	@Autowired
	private KafkaProducer kafkaProducer;
	
	@Autowired
	private CoinInfoMapper CoinInfoMapper;
	
    public CoinTradeSchedulerService(ThreadPoolTaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }
    
	@Transactional
	public void CoinDirectBuySellStart() {
		CoinDirectBuySellStop();
    	System.out.println("start");
        
        job = taskScheduler.schedule(() -> {
			try {
				executeJob();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, new CronTrigger("0/1 * * * * ?")); // 1초마다 실행
	}
	public void CoinDirectBuySellStop() {
        if (job != null) {
        	System.out.println("stopJob");
            job.cancel(true);
        }
    }
	@Transactional
    private void executeJob() throws IOException, NoSuchAlgorithmException, InterruptedException {
		
		List<CoinDto> coins = CoinInfoMapper.getCoin();
		
		System.out.println("??????????" + coins.toString());
		
		try {
			ObjectMapper mapper = new ObjectMapper();
	        String msg = mapper.writeValueAsString(coins);
	        kafkaProducer.sendMessage(msg);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//================================
	//================================
	//================================
}
