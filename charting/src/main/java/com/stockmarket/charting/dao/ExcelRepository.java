package com.stockmarket.charting.dao;

import com.stockmarket.charting.entity.StockPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExcelRepository extends JpaRepository<StockPrice,Long> {
}
