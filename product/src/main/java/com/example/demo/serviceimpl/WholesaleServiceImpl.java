package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.WholesaleEntity;
import com.example.demo.repository.WholesaleRepo;
import com.example.demo.service.WholesaleService;

@Service
public class WholesaleServiceImpl implements WholesaleService {

	@Autowired
	WholesaleRepo wholesaleRepo;
	
	
	public WholesaleEntity saveWholesale(WholesaleEntity wholesale) {
		
		return wholesaleRepo.save(wholesale);
	}

	
	public List<WholesaleEntity> getAll() {
		
		return wholesaleRepo.findAll();
	}

	
	public WholesaleEntity update(WholesaleEntity wholesale) {
		
		return wholesaleRepo.save(wholesale);
		
	}

	
	public void delete(int id) {
		wholesaleRepo.deleteById(id);
		
	}


	
	
}
