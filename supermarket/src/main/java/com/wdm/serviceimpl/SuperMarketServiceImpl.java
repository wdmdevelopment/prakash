package com.wdm.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.dto.ResquestSuperMarket;
import com.wdm.entity.Address;
import com.wdm.entity.SuperMarketEntity;
import com.wdm.repository.SuperMarketRepo;
import com.wdm.service.SuperMarketService;

@Service
public class SuperMarketServiceImpl implements SuperMarketService {
	
	@Autowired
	
	SuperMarketRepo supermarketRepo;

	
	public ResquestSuperMarket save(ResquestSuperMarket supermarket1) {
		
		SuperMarketEntity supermarket = new SuperMarketEntity(supermarket1.getSuperMarketName(),
				supermarket1.getAddress(), supermarket1.getProduct(), supermarket1.getWholesale());
		
		supermarketRepo.save(supermarket);
		
		return mapTosupermarket(supermarket);
	}

	
	


	public List<ResquestSuperMarket> getAll() {
		
		return supermarketRepo.findAll().stream().map(t -> {
			return mapTosupermarket(t);
		}).collect(Collectors.toList());
				
	}

	
	public SuperMarketEntity update(SuperMarketEntity supermodel, long superId) {
		
		return supermarketRepo.save(supermodel);
	}

	
	public void delete(long id) {
		
		supermarketRepo.deleteById(id);
	}


	
	
	
public ResquestSuperMarket mapTosupermarket(SuperMarketEntity supermarket1) {
		
		
		
		return new ResquestSuperMarket(supermarket1.getSuperMarketName(), supermarket1.getAddress(), supermarket1.getProduct(),
				supermarket1.getWholesale());
	}
	
	
}
