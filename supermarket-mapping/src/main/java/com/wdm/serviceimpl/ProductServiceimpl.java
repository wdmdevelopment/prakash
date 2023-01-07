package com.wdm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.entity.Category;
import com.wdm.entity.Product;
import com.wdm.model.RequestProduct;
import com.wdm.repository.ProductMappingRespository;
import com.wdm.service.ProductService;

@Service
public class ProductServiceimpl implements ProductService {
	
	@Autowired
	ProductMappingRespository productRepo;

	
	public Product saveProduct(RequestProduct requestProduct) {
			
		Product product = new Product();
		
		product.setProductName(requestProduct.getProductName());
		
		product.setstock(requestProduct.getStockDetails());
		
		
		
		return productRepo.save(product);
	}

	
	public void deletebyId(long id) {
	
		productRepo.deleteById(id);
	}


	@Override
	public Product getAllproduct() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Product updateProduct(RequestProduct requestProduct, long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
