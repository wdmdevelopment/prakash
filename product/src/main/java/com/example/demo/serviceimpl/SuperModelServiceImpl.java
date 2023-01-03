package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.SuperModelEntity;
import com.example.demo.repository.SuperMarketRepo;
import com.example.demo.service.SuperModelService;

@Service
public class SuperModelServiceImpl implements SuperModelService {
	
	@Autowired
	
	SuperMarketRepo supermarketRepo;

	
	public SuperModelEntity save(SuperModelEntity supermarket) {
		
		return supermarketRepo.save(supermarket);
	}

	
	public List<SuperModelEntity> getAll() {
		
		return supermarketRepo.findAll();
	}

	
	public void update(SuperModelEntity supermodel) {
		
		supermarketRepo.save(supermodel);
	}

	
	public void delete(int id) {
		
		supermarketRepo.deleteById(id);
	}
	
	
	

}
