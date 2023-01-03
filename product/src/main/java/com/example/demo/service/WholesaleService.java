package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.WholesaleEntity;

@Service
public interface WholesaleService {
	
	
	public WholesaleEntity saveWholesale (WholesaleEntity wholesale);
	public List<WholesaleEntity> getAll();
	public WholesaleEntity update(WholesaleEntity wholesale);
	public void delete(int id);
}
