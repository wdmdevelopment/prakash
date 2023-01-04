package com.wdm.service;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wdm.dto.ResquestProduct;
import com.wdm.entity.ProductEntity;

@Service
public interface ProductService {
	
	public List<ResquestProduct> getAllProduct() throws IOException, SQLException;

	public ResquestProduct saveProduct(ResquestProduct productModel);

	public ProductEntity getbyId(long id);

	public ProductEntity updateProduct(ProductEntity productmodel, long id);

	public void deleteProduct(long id);
	
	public List<Date> getunique();
	
	public List<ProductEntity> getNuts();
	
	public ResquestProduct mapToProduct(ProductEntity product);

}
