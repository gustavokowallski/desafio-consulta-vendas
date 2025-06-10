package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SummaryMinDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
		LocalDate dataInicial;
		LocalDate dataFinal;
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		if( minDate == null ||  minDate.isBlank()){
			dataInicial = today.minusYears(1L);
		}
		else{
			dataInicial = LocalDate.parse(minDate);
		}
		if( maxDate == null  || maxDate.isBlank()){
			dataFinal = today;
		}
		else{
			dataFinal = LocalDate.parse(maxDate);
		}


		return repository.search1(dataInicial, dataFinal, name);
	}
}
