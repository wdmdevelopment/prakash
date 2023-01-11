package com.wdm.serviceimpl;

 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.entity.Address;
import com.wdm.entity.Supermarket;
import com.wdm.exception.ProductCustomException;
import com.wdm.model.RequestSuperMarket;
import com.wdm.repository.SuperMarkerRespository;
import com.wdm.service.SuperMarketService;

@Service
public class SuperMarketServiceimpl implements SuperMarketService {

	@Autowired

	SuperMarkerRespository SupermarketRepo;

	
	public Supermarket saveSuperMarket(RequestSuperMarket requestSuperMarket) throws Exception {

	try {	
//		Address address = new Address(requestSuperMarket.getDoorNo(), requestSuperMarket.getStreet(),
//				requestSuperMarket.getCity(), requestSuperMarket.getState(), requestSuperMarket.getCountry());
		 
		
		Supermarket supermarket = new Supermarket();

		supermarket.setSuperMarketName(requestSuperMarket.getSuperMarketName());
		Address address = new Address();
		address.setdoorNo(requestSuperMarket.getDoorNo());
		address.setState(requestSuperMarket.getState());
		
		address.setStreet(requestSuperMarket.getStreet());
		address.setCity(requestSuperMarket.getCity());
		address.setCountry(requestSuperMarket.getCountry());
		
		supermarket.setAddress(address);

		return SupermarketRepo.save(supermarket);
	}
	catch (Exception e) {
		throw new ProductCustomException("Invalid"+e.getMessage());
	}
	}
	
	

	public void delete(long id) {

		SupermarketRepo.deleteById(id);

	}

	public List<Supermarket> getSupermarket() {

		return  SupermarketRepo.findAll();
	}

	public Supermarket updatesupermarket(Supermarket supermarket, long id) {

		return SupermarketRepo.save(supermarket);
	}

}
