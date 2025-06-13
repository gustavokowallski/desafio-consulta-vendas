package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.RelatoryDTO;
import com.devsuperior.dsmeta.dto.SummaryMinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<SummaryMinDTO> searchSummary(String minDate, String maxDate, String name){
		LocalDate dataInicial = validationMinDate(minDate);
		LocalDate dataFinal = validationMaxDate(maxDate);
		return repository.search1(dataInicial, dataFinal, name);
	}

	@Transactional(readOnly = true)
	public Page<RelatoryDTO> searchRelatory(String minDate, String maxDate, String name, Pageable pageable){
		LocalDate dataInicial = validationMinDate(minDate);
		LocalDate dataFinal = validationMaxDate(maxDate);
		return repository.relatory(dataInicial, dataFinal, name, pageable);
	}


	private LocalDate validationMinDate(String date){
		LocalDate data;
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		if( date == null ||  date.isBlank()){
			data = today.minusYears(1L);
		}
		else{
			data = LocalDate.parse(date);
		}
		return data;

	}

	private LocalDate validationMaxDate(String date){
		LocalDate data;
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		if( date == null ||  date.isBlank()){
			data = today;
		}
		else{
			data = LocalDate.parse(date);
		}
		return data;

	}
}
