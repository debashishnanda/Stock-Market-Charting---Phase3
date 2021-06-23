package com.stockmarket.charting.model;

import com.stockmarket.charting.entity.StockExchange;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Company {
    private long id;
    private String companyName;
    private float companyTurnover;
    private String companyCEO;
    private String boardOfDirectors;
    private List<StockExchange> listedExchanges = new ArrayList<>();
    private String sector ;
    private String companyDesc;
}
