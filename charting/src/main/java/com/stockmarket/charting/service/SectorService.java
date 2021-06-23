package com.stockmarket.charting.service;

import com.stockmarket.charting.entity.Company;
import com.stockmarket.charting.entity.Sector;
import com.stockmarket.charting.dao.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SectorService {

    @Autowired
    SectorRepository sectorRepository;


    @Transactional
    public void addSector(Sector sector){
        sectorRepository.save(sector);
    }

    public  List<Sector> getAll(){
        return sectorRepository.findAll();
    }

//	public void addCompanyToSector(Sector sector, Company company) {
//		// TODO Auto-generated method stub
//		
//	}

}
