package com.stockmarket.charting.dao;

import com.stockmarket.charting.entity.StockPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice,Long> {

    List<StockPrice> findStockPriceByCompanyCode(String companyCode);

	List<StockPrice> findStockPriceByCompanyCodeAndDateBetween(String code, Date startDate, Date endDate);

//    @Query("SELECT u FROM StockPrice u WHERE u.companyCode = :companyCode and u.date BETWEEN :start AND :end")
//    List<StockPrice> findStockPriceByDate(
//            @Param("companyCode") String companyCode,
//            @Param("start") Date name,
//            @Param("end") Date end
//            );


}
