package com.wdm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wdm.dto.ResquestSuperMarket;
import com.wdm.entity.SuperMarketEntity;

@Service
public interface SuperMarketService {
	
	public ResquestSuperMarket save (ResquestSuperMarket supermarket);
	public List<ResquestSuperMarket> getAll();
	public SuperMarketEntity update(SuperMarketEntity supermodel, long id);
	public void delete(long id);
}
