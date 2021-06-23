package com.stockmarket.charting.model;

import com.stockmarket.charting.entity.Company;
import com.stockmarket.charting.entity.StockExchange;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CompanyIO {

    private Company company;

    private List<String> companyCode;

    public CompanyIO(Company company, List<String> companyCode) {
        this.company = company;
        this.companyCode = companyCode;
    }

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<String> getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(List<String> companyCode) {
		this.companyCode = companyCode;
	}

	
}