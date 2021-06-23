package com.stockmarket.charting.service;

import com.stockmarket.charting.entity.Company;
import com.stockmarket.charting.entity.IPODetails;
import com.stockmarket.charting.dao.IPODetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPOService {
    @Autowired
    IPODetailsRepository ipoDetailsRepository;

    public List<IPODetails> getCompanyIPO(Company company){
        return ipoDetailsRepository.findIPODetailsByCompanyName(company.getCompanyName());
    }
    public void addIPO(IPODetails ipo){
        ipoDetailsRepository.save(ipo);
    }
	public List<IPODetails> getAllIPO() {
		// TODO Auto-generated method stub
		return ipoDetailsRepository.findAll();
	}
}
