package com.stockmarket.charting.controller;

import com.stockmarket.charting.entity.Company;
import com.stockmarket.charting.entity.Sector;
import com.stockmarket.charting.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/sector")

@CrossOrigin(origins = "*", maxAge = 3600)
public class SectorController {

    @Autowired
    SectorService sectorService;

    @PostMapping("/add")
    public ResponseEntity<?> addSector(@RequestBody Sector sector){

        sectorService.addSector(sector);
        return ResponseEntity.ok(new String("Sector added successfully!"));
    }

//    @PostMapping("/add/company")
//    public ResponseEntity<?> addCompanyToSector(@RequestBody Company company){
//        Sector sector = company.getSector();
//
//        sectorService.addCompanyToSector(sector,company);
//        return ResponseEntity.ok(new String("Company added to sector successfully!"));
//    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllSector(){
        return ResponseEntity.ok(sectorService.getAll());
    }

}

