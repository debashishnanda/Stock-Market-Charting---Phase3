package com.stockmarket.charting.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class StockPriceIO {

    private long companyID;
    private long exchangeID;
    private Date startDate;
    private Date endDate;
	public long getCompanyID() {
		return companyID;
	}
	public void setCompanyID(long companyID) {
		this.companyID = companyID;
	}
	public long getExchangeID() {
		return exchangeID;
	}
	public void setExchangeID(long exchangeID) {
		this.exchangeID = exchangeID;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}