package com.wdm.service;
 

import com.wdm.entity.Supermarket;
import com.wdm.model.RequestSuperMarket;

public interface SuperMarketService {
	
	public Supermarket saveSuperMarket(RequestSuperMarket request);
	
	public void delete(long id);
	
	public Supermarket getSupermarket();
	
	public Supermarket updatesupermarket(RequestSuperMarket request, long id);

}
