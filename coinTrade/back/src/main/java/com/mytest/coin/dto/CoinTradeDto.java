package com.mytest.coin.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CoinTradeDto {
	private int rowId;
	private String userId;
	private String market;
	private int buyCnt;
	private int sellCnt;
	private boolean useYn;
	private double priceWon;
	private double lastBuyPrice;
	private double lastSellPrice;
	private double fixPrice;
	private double fixUpPrice;
	private double lockTop;
	private double lockBottom;
	private double useKrw;
	private double backKrw;
	private double reGet;
	private Timestamp createDt;
	private Timestamp updateDt;
}
