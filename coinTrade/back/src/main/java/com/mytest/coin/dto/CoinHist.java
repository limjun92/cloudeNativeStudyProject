package com.mytest.coin.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CoinHist {
	private int rowId;
	private String type;
	private String market;
	private boolean useYn;
	private double volume;
	private double buyPrice;
	private double buyWonPay;
	private double sellPrice;
	private double sellWonPay;
	private double setFixPrice;
	private double profit;
	private Timestamp createDt;
	private Timestamp updateDt;
}
