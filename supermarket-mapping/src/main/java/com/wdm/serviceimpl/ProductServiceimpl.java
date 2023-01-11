package com.wdm.serviceimpl;

 
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.wdm.entity.ImageProduct;
import com.wdm.entity.Product;
import com.wdm.exception.IdNotFoundException;
import com.wdm.exception.InvalidDataException;
import com.wdm.exception.ProductNotFoundException;
import com.wdm.model.RequestProduct;
import com.wdm.repository.ImageProductRepository;
import com.wdm.repository.ProductMappingRespository;
import com.wdm.response.ProductResponse;
import com.wdm.service.ProductService;

@Service
 
 
public class ProductServiceimpl implements ProductService {

	@Autowired
	ProductMappingRespository productRepo;
	
	@Autowired
	ImageProductRepository imageRepo;
	
	@Transactional
	public ImageProduct saveProduct(RequestProduct requestProduct, MultipartFile file) throws IOException {
		
		 String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		 ImageProduct img = new ImageProduct();
		 img.setImageName(fileName);
		 img.setImageType(file.getContentType());
		 img.setImageData(file.getBytes());
		
		 Product product = new Product();

		product.setProductName(requestProduct.getProductName());

		product.setStockDetails(requestProduct.getStockDetails());
			
		productRepo.save(product);
		
		img.setProduct(product);
			
		
		return imageRepo.save(img);
	}

	public void deletebyId(long id) {
		try {
			productRepo.deleteById(id);
		} catch (Exception e) {

			throw new IdNotFoundException("Product with given with Id: " + id +e);

		}

	}

	public List<Product> getAllproduct() {

		return productRepo.findAll();
		   
				 
				  
		   
 
	}




	public Product updateProduct(RequestProduct product, long id) {
		
		Optional<Product> findById = productRepo.findById(id);
		Product p = new Product();
		if(findById.isPresent()) {
			findById.get();
			p.setProductName(product.getProductName());
			p.setStockDetails(product.getStockDetails());
			
			//p.setCategory(product.getCategory());
			 
			
		}
		return productRepo.save(p);
		
	}
	
	
	

	public Optional<Product> getProductById(long productId) {
		Optional<Product> product;
		try {
			product = productRepo.findById(productId);
		}

		catch (Exception e) {
			throw new ProductNotFoundException("Product Not found" + productId + e);
		}

		return product;
	}

	public ProductResponse mapToProduct(Product product) {

		ProductResponse requestProduct = new ProductResponse();

		requestProduct.setProductName(product.getProductName());
		requestProduct.setStockDetails(product.getStockDetails());

		return requestProduct;
	}

	
	public Product store(MultipartFile file) throws IOException, SerialException, SQLException {
		
		try {
		Product product = new Product();
			//product.setProductImage(new SerialBlob(file.getBytes()));
		
			return productRepo.save(product);
	}
		catch (Exception e) {
			 throw new ProductNotFoundException("File Not Found");
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

	 

	public List<Product> filterbyId(String pName) {
		List<Product> findByfilterproduct = null;
		try {
			findByfilterproduct = productRepo.findByfilterproduct(pName);	
		} catch (Exception e) {
			throw new InvalidDataException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString());
		}
		
		return 	findByfilterproduct;	
		 
		 
			 
	}
 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
