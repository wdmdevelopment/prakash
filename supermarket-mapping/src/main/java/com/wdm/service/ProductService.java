package com.wdm.service;

 
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wdm.entity.ImageProduct;
import com.wdm.entity.Product;
 
import com.wdm.model.RequestProduct;
 

@Service
public interface ProductService {
	
	
	public ImageProduct saveProduct (RequestProduct requestProduct, MultipartFile file) throws IOException;
	
	public void deletebyId(long id);
	
	public List<Product> getAllproduct();
	
	public Product updateProduct(RequestProduct product, long id);
	
	public Optional<Product> getProductById(long productId);
	
	public List<Product> filterbyId(String pName);
	
	
	public Product store(MultipartFile file) throws IOException, SerialException, SQLException;
//	public ProductResponse getFileById(long id)throws IOException, SQLException;
	//public List<ProductResponse> getFileList();
	//public ProductResponse mapToFileResponse(Product productfile) throws IOException, SQLException;
 
	
	

}
