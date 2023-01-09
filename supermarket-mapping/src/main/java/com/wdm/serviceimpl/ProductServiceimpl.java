package com.wdm.serviceimpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
 
import com.wdm.entity.Product;

import com.wdm.exception.IdNotFoundException;
import com.wdm.exception.ProductAlreadyExistsException;
import com.wdm.exception.ProductNotFoundException;
import com.wdm.model.RequestProduct;
import com.wdm.repository.ProductMappingRespository;
import com.wdm.response.ProductResponse;
import com.wdm.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceimpl implements ProductService {

	@Autowired
	ProductMappingRespository productRepo;

	public Product saveProduct(RequestProduct requestProduct) {
		
		// log.info("ProductServiceimpl | addProduct is called");
		
		Product product = new Product();

		product.setProductName(requestProduct.getProductName());

		product.setstock(requestProduct.getStockDetails());

		if (productRepo.existsById(product.getProductId())) {

			throw new ProductAlreadyExistsException("Product Already Exist");
		}

		return productRepo.save(product);
	}

	public void deletebyId(long id) {
		try {
			productRepo.deleteById(id);
		} catch (Exception e) {

			throw new IdNotFoundException("Product with given with Id: " + id + " not found");

		}

	}

	public Product getAllproduct() {

		return (Product) productRepo.findAll().stream().map(t -> {
			return mapToProduct(t);
		}).collect(Collectors.toList());
	}




	public Product updateProduct(Product product, long id) {

		return productRepo.save(product);
	}
	
	
	

	public Optional<Product> getProductById(long productId) {
		Optional<Product> product;
		try {
			product = productRepo.findById(productId);
		}

		catch (Exception e) {
			throw new ProductNotFoundException("Product Not found" + productId);
		}

		return product;
	}

	public RequestProduct mapToProduct(Product product) {

		RequestProduct requestProduct = new RequestProduct();

		requestProduct.setProductName(product.getProductName());
		requestProduct.setStockDetails(product.getstock());

		return requestProduct;
	}

	
	public Product store(MultipartFile file) throws IOException, SerialException, SQLException {
		
		try {
		Product product = new Product();
			product.setData(new SerialBlob(file.getBytes()));
			product.setName(file.getOriginalFilename());
			product.setType(file.getContentType());
			
			return productRepo.save(product);
	}
		catch (Exception e) {
			 throw new ProductNotFoundException("FIle Not Found");
		}
	}
	
	
	
//	public List<ProductResponse> getFileList() {
//		return productRepo.findAll().stream().map(t -> {
//			try {
//				return mapToFileResponse(t);
//			} catch (IOException e) {
//				
//				e.printStackTrace();
//			} catch (SQLException e) {
//				
//				e.printStackTrace();
//			}
//			return null;
//		}).collect(Collectors.toList());
//		
//	}
	
	
	
	

	
//	public ProductResponse getFileById(long id) throws IOException, SQLException {
//		 	
//		Optional<Product> product = productRepo.findById(id);
//		if(product.isEmpty()) {
//			return mapToFileResponse(product.get());
//		}
//		return null;
//	}

	 
//	public ProductResponse mapToFileResponse(Product product)  throws IOException, SQLException{
//		ProductResponse productresponse = new ProductResponse();
//		productresponse.getData();
//		productresponse.getName();
//		productresponse.getType();
//		return productresponse;
//	}

	 

	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
