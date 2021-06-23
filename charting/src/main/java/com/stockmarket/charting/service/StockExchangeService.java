package com.stockmarket.charting.service;

import com.stockmarket.charting.entity.StockExchange;
import com.stockmarket.charting.dao.StockExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StockExchangeService {

    @Autowired
    StockExchangeRepository stockExchangeRepository;

    @Transactional
    public void saveAll(List<StockExchange> stockExchangeList){
        stockExchangeRepository.saveAll(stockExchangeList);
    }

    @Transactional
    public void saveStockExchange(StockExchange stockExchange){
        stockExchangeRepository.save(stockExchange);
    }

    public List<StockExchange> getAllStockExchange(){
        return stockExchangeRepository.findAll();
    }
}
