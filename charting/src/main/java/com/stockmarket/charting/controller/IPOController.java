package com.stockmarket.charting.controller;

import com.stockmarket.charting.entity.Company;
import com.stockmarket.charting.entity.IPODetails;
import com.stockmarket.charting.model.MessageResponse;
import com.stockmarket.charting.service.IPOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/ipo")
@CrossOrigin(origins = "*", maxAge = 3600)

public class IPOController {

    @Autowired
    IPOService ipoService;

    @GetMapping("/all")
    public ResponseEntity<?> getIPOs(){
        return ResponseEntity.ok(ipoService.getAllIPO());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addIPO(@RequestBody IPODetails ipo){
        ipoService.addIPO(ipo);
        return ResponseEntity.ok(new MessageResponse("IPO added successfully!"));
    }
}