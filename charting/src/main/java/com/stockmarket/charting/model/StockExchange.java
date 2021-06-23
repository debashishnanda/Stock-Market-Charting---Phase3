package com.stockmarket.charting.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockExchange {
    private long id;
    private String stockExchange;
    private String brief;
    private String contactAddress;
    private String remarks;
}