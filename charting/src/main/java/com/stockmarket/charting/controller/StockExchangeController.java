package com.stockmarket.charting.controller;

import com.stockmarket.charting.entity.StockExchange;
import com.stockmarket.charting.model.Company;
import com.stockmarket.charting.service.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Controller
@RequestMapping("/api/exchange")
@CrossOrigin(origins = "*", maxAge = 3600)
public class StockExchangeController {

    @Autowired
    StockExchangeService stockExchangeService;

    @PostMapping("/add")
    public ResponseEntity<?> addStockExchange(@RequestBody StockExchange stockExchange){
        stockExchangeService.saveStockExchange(stockExchange);
        return ResponseEntity.ok(new String("Exchange added successfully!"));
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllExchange(){

        return ResponseEntity.ok(stockExchangeService.getAllStockExchange());
    }

    @PostMapping("/companies")
    public ResponseEntity<?> getAllCompaniesInExchange(@RequestBody StockExchange stockExchange){

        //REST template call to company-service
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));



        HttpEntity<StockExchange> entity = new HttpEntity<>(stockExchange, headers);
//        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8081/api/company/companies", entity,String.class);

        ResponseEntity<List<Optional<Company>>> response = restTemplate.exchange("http://localhost:8080/api/company/companies", HttpMethod.POST, entity, new ParameterizedTypeReference<List<Optional<Company>>>() {});
//        System.out.println(response.getBody());
        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(){
        List<StockExchange> stockExchangeList = new ArrayList<>();
        stockExchangeList.add(new StockExchange(1,"NYSE"));
        stockExchangeList.add(new StockExchange(2,"NASDAQ"));
        stockExchangeList.add(new StockExchange(3,"BSE"));
        stockExchangeList.add(new StockExchange(4,"NSE"));
        stockExchangeList.add(new StockExchange(5,"LSE"));
        stockExchangeService.saveAll(stockExchangeList);
        return ResponseEntity.ok(new String("Updated successfully!"));
    }
}