package com.stockmarket.charting.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Company_Exchanges {

    @Id
    @GeneratedValue
    private long id;

    @JoinColumn(name = "company_id")
    private long companyId;

    @JoinColumn(name = "stock_exchange_id")
    private long stockExchangeId;

    private String companyCodeInExchange;

    public Company_Exchanges(long companyId, long stockExchangeId, String companyCodeInExchange) {
        this.companyId = companyId;
        this.stockExchangeId = stockExchangeId;
        this.companyCodeInExchange = companyCodeInExchange;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public long getStockExchangeId() {
		return stockExchangeId;
	}

	public void setStockExchangeId(long stockExchangeId) {
		this.stockExchangeId = stockExchangeId;
	}

	public String getCompanyCodeInExchange() {
		return companyCodeInExchange;
	}

	public void setCompanyCodeInExchange(String companyCodeInExchange) {
		this.companyCodeInExchange = companyCodeInExchange;
	}

	
}
