package com.stockmarket.charting.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Company_Exchanges implements Serializable {

    private long id;
    private long companyId;
    private long stockExchangeId;
    private String companyCodeInExchange;

}