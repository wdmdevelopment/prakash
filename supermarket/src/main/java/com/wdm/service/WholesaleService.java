package com.wdm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wdm.dto.RequestWholesale;
import com.wdm.entity.WholesaleEntity;

@Service
public interface WholesaleService {
	
	public RequestWholesale saveWholesale (RequestWholesale wholesale);
	public List<WholesaleEntity> getAll();
	public WholesaleEntity update(WholesaleEntity wholesale);
	public void delete(long id);
}
