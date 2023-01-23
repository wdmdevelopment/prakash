package com.wdm.service;

 
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wdm.entity.Product;
import com.wdm.model.RequestProduct;
import com.wdm.response.ProductResponse;
 

@Service
public interface ProductService {
	
	
	public Product saveProduct (String requestProduct, MultipartFile file) throws IOException;
	
	public void deletebyId(long id);
	
	public List<ProductResponse> getAllproduct();
	
	public Product updateProduct(String requestProduct, MultipartFile file, long id) throws Exception;
	
	public Optional<Product> getProductById(long productId);
	
	public List<ProductResponse> filterbyId(String pName);
	
	 
	
	

}
