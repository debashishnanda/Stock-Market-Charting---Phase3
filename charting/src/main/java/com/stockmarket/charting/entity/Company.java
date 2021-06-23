package com.stockmarket.charting.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;


@Entity
@Data
@NoArgsConstructor
public class Company {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private String companyName;
    private float companyTurnover;
    private String companyCEO;
    private String boardOfDirectors;
    @ManyToMany
    private List<StockExchange> listedExchanges = new ArrayList<>();
    @ManyToOne
    private Sector sector ;
    private String companyDesc;

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public float getCompanyTurnover() {
		return companyTurnover;
	}

	public void setCompanyTurnover(float companyTurnover) {
		this.companyTurnover = companyTurnover;
	}

	public String getCompanyCEO() {
		return companyCEO;
	}

	public void setCompanyCEO(String companyCEO) {
		this.companyCEO = companyCEO;
	}

	public String getBoardOfDirectors() {
		return boardOfDirectors;
	}

	public void setBoardOfDirectors(String boardOfDirectors) {
		this.boardOfDirectors = boardOfDirectors;
	}

	public List<StockExchange> getListedExchanges() {
		return listedExchanges;
	}

	public void setListedExchanges(List<StockExchange> listedExchanges) {
		this.listedExchanges = listedExchanges;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public String getCompanyDesc() {
		return companyDesc;
	}

	public void setCompanyDesc(String companyDesc) {
		this.companyDesc = companyDesc;
	}

	public Company(String companyName, float companyTurnover, String companyCEO, List<StockExchange> listedExchanges) {
        this.companyName = companyName;
        this.companyTurnover = companyTurnover;
        this.companyCEO = companyCEO;
        this.listedExchanges = listedExchanges;

    }

    public void addStockExchange(StockExchange stockExchange){
        listedExchanges.add(stockExchange);
    }

}
