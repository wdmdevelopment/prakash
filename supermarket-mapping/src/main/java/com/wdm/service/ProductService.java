package com.wdm.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

 
import com.wdm.entity.Product;
 
import com.wdm.model.RequestProduct;
import com.wdm.response.ProductResponse;

@Service
public interface ProductService {
	
	
	public Product saveProduct (RequestProduct requestProduct);
	
	public void deletebyId(long id);
	
	public List<Product> getAllproduct();
	
	public Product updateProduct(Product product, long id);
	
	public Optional<Product> getProductById(long productId);
	
	
	
	
	public Product store(MultipartFile file) throws IOException, SerialException, SQLException;
//	public ProductResponse getFileById(long id)throws IOException, SQLException;
	//public List<ProductResponse> getFileList();
	//public ProductResponse mapToFileResponse(Product productfile) throws IOException, SQLException;
 
	
	

}
