package com.wdm.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.dto.RequestWholesale;
import com.wdm.entity.WholesaleEntity;
import com.wdm.repository.WholesaleRepo;
import com.wdm.service.WholesaleService;

@Service
public class WholesaleServiceImpl implements WholesaleService {

	@Autowired
	WholesaleRepo wholesaleRepo;
	
	
	public RequestWholesale saveWholesale(RequestWholesale wholesale) {
		
			WholesaleEntity wholesale1 = new WholesaleEntity(wholesale.getName(), wholesale.getSupermarket());
		
			wholesaleRepo.save(wholesale1);
			
			return mapToWholesale(wholesale1);
	}

	
	

	public List<WholesaleEntity> getAll() {
		
		return wholesaleRepo.findAll();
	}

	
	public WholesaleEntity update(WholesaleEntity wholesale) {
		
		return wholesaleRepo.save(wholesale);
		
	}

	
	public void delete(long id) {
		wholesaleRepo.deleteById(id);
		
	}


	public RequestWholesale mapToWholesale(WholesaleEntity wholesale1) {
		
		return new RequestWholesale(wholesale1.getName(), wholesale1.getSupermarket());
	}




}
