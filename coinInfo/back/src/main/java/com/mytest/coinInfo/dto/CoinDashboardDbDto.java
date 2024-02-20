package com.mytest.coinInfo.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CoinDashboardDbDto {
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
	
    public CoinDashboardDbDto() {
    }
	
	public CoinDashboardDbDto(int rowId, String userId, String market, int buyCnt, int sellCnt, boolean useYn,
			double priceWon, double lastBuyPrice, double lastSellPrice, double fixPrice, double fixUpPrice,
			double lockTop, double lockBottom, double useKrw, double backKrw, double reGet, Timestamp createDt,
			Timestamp updateDt) {
		super();
		this.rowId = rowId;
		this.userId = userId;
		this.market = market;
		this.buyCnt = buyCnt;
		this.sellCnt = sellCnt;
		this.useYn = useYn;
		this.priceWon = priceWon;
		this.lastBuyPrice = lastBuyPrice;
		this.lastSellPrice = lastSellPrice;
		this.fixPrice = fixPrice;
		this.fixUpPrice = fixUpPrice;
		this.lockTop = lockTop;
		this.lockBottom = lockBottom;
		this.useKrw = useKrw;
		this.backKrw = backKrw;
		this.reGet = reGet;
		this.createDt = createDt;
		this.updateDt = updateDt;
	}
	
	
}
