package com.wdm.service;
 

 

import java.rmi.ServerException;
import java.util.List;

import com.wdm.entity.Supermarket;
import com.wdm.model.RequestSuperMarket;
 

public interface SuperMarketService {
	
	public Supermarket saveSuperMarket(RequestSuperMarket request) throws ServerException, Exception;
	
	public void delete(long id);
	
	public List<Supermarket> getSupermarket();
	
	public Supermarket updatesupermarket(Supermarket supermarket, long id);

}
