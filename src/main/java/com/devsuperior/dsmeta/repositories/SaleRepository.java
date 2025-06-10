package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.RelatoryDTO;
import com.devsuperior.dsmeta.dto.SummaryMinDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SummaryMinDTO(obj.seller.name, ROUND(SUM(obj.amount), 1)) " +
            "FROM Sale obj " +
            "JOIN obj.seller  " +
            "WHERE obj.date BETWEEN :minTime AND :maxTime " +
            "AND UPPER(obj.seller.name) LIKE CONCAT('%', UPPER(:name), '%')" +
            "GROUP BY obj.seller.name")
    List<SummaryMinDTO> search1 (LocalDate minTime, LocalDate maxTime, String name);

    @Query("SELECT new com.devsuperior.dsmeta.dto.RelatoryDTO(obj.seller.id, obj.date, obj.amount, obj.seller.name) " +
            "FROM Sale obj " +
            "JOIN obj.seller " +
            "WHERE obj.date BETWEEN :minTime AND :maxTime " +
            "AND UPPER(obj.seller.name) LIKE CONCAT('%', UPPER(:name), '%')" )
    Page<RelatoryDTO> relatory (LocalDate minTime, LocalDate maxTime, String name, Pageable pageable);




}

