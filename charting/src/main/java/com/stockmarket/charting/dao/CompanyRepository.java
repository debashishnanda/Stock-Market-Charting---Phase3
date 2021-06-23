package com.stockmarket.charting.dao;

import com.stockmarket.charting.entity.Company;
import com.stockmarket.charting.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByCompanyName(String companyName);
    List<Optional<Company>> findCompanyBySector(Sector sector);
}