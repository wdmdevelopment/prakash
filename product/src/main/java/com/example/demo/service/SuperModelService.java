package com.example.demo.service;

import java.util.List;

import com.example.demo.model.SuperModelEntity;

public interface SuperModelService {
	
	public SuperModelEntity save (SuperModelEntity supermarket);
	public List<SuperModelEntity> getAll();
	public void update(SuperModelEntity supermodel);
	public void delete(int id);

}
