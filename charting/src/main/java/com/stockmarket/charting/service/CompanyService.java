package com.stockmarket.charting.service;


import com.stockmarket.charting.entity.*;
import com.stockmarket.charting.model.CompanyIO;
import com.stockmarket.charting.dao.CompanyRepository;
import com.stockmarket.charting.dao.Company_ExchangesRepository;
import com.stockmarket.charting.dao.IPODetailsRepository;
import com.stockmarket.charting.dao.StockPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    StockPriceRepository stockPriceRepository;

    @Autowired
    Company_ExchangesRepository company_exchangesRepository;


    @Transactional
    public void addNewCompany(CompanyIO companyIO){
        Company newCompany = companyIO.getCompany();
        Company company = companyRepository.save(newCompany);
        List<StockExchange> stockExchangeList = company.getListedExchanges();
        List<String> companyCodeList = companyIO.getCompanyCode();

        Iterator<StockExchange> itrSE = stockExchangeList.iterator();
        Iterator<String> itrCC = companyCodeList.iterator();
       while(itrCC.hasNext() && itrSE.hasNext()){
            Company_Exchanges company_exchanges = new Company_Exchanges(company.getId(),itrSE.next().getId(),itrCC.next());
            company_exchangesRepository.save(company_exchanges);
        }

    }

    @Transactional
    public void updateCompany(CompanyIO companyIO){
        Company updateCompany = companyIO.getCompany();
        try{
            companyRepository.findById(updateCompany.getId()).isPresent();
            Company company = companyRepository.save(updateCompany);
            List<StockExchange> stockExchangeList = company.getListedExchanges();
            List<String> companyCodeList = companyIO.getCompanyCode();

            Iterator<StockExchange> itrSE = stockExchangeList.iterator();
            Iterator<String> itrCC = companyCodeList.iterator();
            while(itrCC.hasNext() && itrSE.hasNext()){
                Company_Exchanges company_exchanges = new Company_Exchanges(company.getId(),itrSE.next().getId(),itrCC.next());
                company_exchangesRepository.save(company_exchanges);
            }

        }catch (RuntimeException e){

        }
    }

    public Optional<Company> getCompanyByName(String name){
        return companyRepository.findByCompanyName(name);
    }

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    public List<StockPrice> getCompanyStockPrice(long companyID, long exchangeID, Date startDate, Date endDate){
        String code = company_exchangesRepository.findCompany_ExchangesByCompanyIdAndStockExchangeId(companyID,exchangeID).getCompanyCodeInExchange();
        return stockPriceRepository.findStockPriceByCompanyCodeAndDateBetween(code,startDate,endDate);
    }

    public List<StockPrice> getCompanyStockPriceByDate(String code, Date startDate, Date endDate){

        return stockPriceRepository.findStockPriceByCompanyCode(code).stream().filter(s ->
            s.getDate().after(startDate) && s.getDate().before(endDate)
        ).collect(Collectors.toList());
    }

    public StockPrice getLatestStockPrice(String code){
        return stockPriceRepository.findStockPriceByCompanyCode(code).stream().reduce((f, s) -> {if(s.getDate().after(f.getDate())){
            return s;
        }else{ return  f ;}})
                .orElse(null);
    }

    public List<Optional<Company>> getCompanyListInStockExchanges(StockExchange stockExchange){
        List<Company_Exchanges> company_exchangesList = company_exchangesRepository.findCompany_ExchangesByStockExchangeId(stockExchange.getId());
        List<Optional<Company>> companyList =company_exchangesList.stream().map(c -> { return companyRepository.findById(c.getCompanyId());}). collect(Collectors.toList());;
        return companyList ;
    }

    public List<Optional<Company>> getCompanyListInSector(Sector sector){
        return companyRepository.findCompanyBySector(sector);
    }

    @Transactional
    public void deleteCompany(Company company){
        List<Company_Exchanges> company_exchangesList = company_exchangesRepository.findCompany_ExchangesByCompanyId(company.getId());
        company_exchangesList.stream().forEach(c -> company_exchangesRepository.delete(c));
        companyRepository.delete(company);
    }

}