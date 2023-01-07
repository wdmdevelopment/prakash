package com.wdm.serviceimpl;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.entity.Address; 
import com.wdm.entity.Supermarket;
import com.wdm.model.RequestSuperMarket;
import com.wdm.repository.SuperMarkerRespository;
import com.wdm.service.SuperMarketService;



@Service
public class SuperMarketServiceimpl implements SuperMarketService {
	
	@Autowired
	
	SuperMarkerRespository SupermarketRepo;

	
	public Supermarket saveSuperMarket(RequestSuperMarket request) {
		
		Supermarket supermarket = new Supermarket();
		
		supermarket.setSuperMarketName(request.getSuperMarketName());
		
		
		Address address = new Address();
		address.setCity(request.getCity());
		address.setCountry(request.getCountry());
		address.setdoorNo(request.getDoorNo());
		address.setState(request.getState());
		address.setStreet(request.getStreet());
		
		supermarket.setAddress(address);
		 
		 
		return SupermarketRepo.save(supermarket);
	}

	
	public void delete(long id) {
		
		SupermarketRepo.deleteById(id);
		
	}

}
