package com.wdm.serviceimpl;

import java.io.IOException;
 
import java.util.Base64;
import java.util.List;
import java.util.Optional;
 
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wdm.controller.ProductController;
import com.wdm.entity.Category;
import com.wdm.entity.ImageProduct;
import com.wdm.entity.Product;
import com.wdm.entity.UserAccount;
import com.wdm.exception.IdNotFoundException;
import com.wdm.exception.InvalidDataException;
import com.wdm.exception.ProductCustomException;
import com.wdm.exception.ProductNotFoundException;
import com.wdm.model.RequestProduct;
import com.wdm.repository.CategoryRepository;
import com.wdm.repository.ImageProductRepository;
import com.wdm.repository.ProductMappingRespository;
import com.wdm.repository.UserAccountRespository;
import com.wdm.response.ProductResponse;
import com.wdm.response.ResponseUpdateProduct;
import com.wdm.service.ProductService;

@Service

public class ProductServiceimpl implements ProductService {

	
	private static final Logger logger = LogManager.getLogger(ProductServiceimpl.class);
	@Autowired
	ProductMappingRespository productRepo;

	@Autowired
	ImageProductRepository imageRepo;
	
	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired
	UserAccountRespository useraccountRepo;

	ObjectMapper mapper = new ObjectMapper();
	
	@Transactional
	public Product saveProduct(String requestProduct, MultipartFile file) throws IOException {
 

		try {
			
			 
			RequestProduct product = mapper.readValue(requestProduct, RequestProduct.class);
			
			UserAccount useraccount = useraccountRepo.findById(product.getUserId())
					.orElseThrow(() -> new IdNotFoundException("Id Not Found"));
			
			String userId = useraccount.getuserRoll();
			 	 
			if(userId.equalsIgnoreCase("admin")) {
				Product product1 = new Product();

				product1.setProductName(product.getProductName());

				product1.setStockDetails(product.getStockDetails());
				
				Category category = categoryRepo.findById(product.getCategoryId())
						.orElseThrow(() -> new IdNotFoundException("Id not found"));
				 
				product1.setCategory(category);
				
				 
				
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());

				ImageProduct img = new ImageProduct();
				img.setImageName(fileName);
				img.setImageType(file.getContentType());
				img.setImageData(file.getBytes());


				img.setProduct(product1);
				imageRepo.save(img);

				return productRepo.save(product1);
			}

			else {
				throw new ProductCustomException("you are not admin can't add product"+userId);
			}

		} catch (Exception e) {
			throw new ProductCustomException("Invalid" + e.getMessage());
		}
	}
	
	 

	public void deletebyId(long id) {

		productRepo.findById(id).orElseThrow(() -> new ProductCustomException("Not Found"));

		productRepo.deleteById(id);

	}

	public List<ProductResponse> getAllproduct() {
		
		try {
		
			List<ProductResponse> collect = productRepo.findAll().stream().map(e -> new ProductResponse(e.getProductId(),
					e.getProductName(), e.getStockDetails(),
e.getCategory().getCategoryName(), e.getProductImage())).collect(Collectors.toList());
			
			System.out.println(collect);
			return collect;
			
		}
		catch (NotFoundException e) {
			throw new NotFoundException(e.getMessage());
		}
			 
		 

		 

	}

	public Product updateProduct(String updateProduct, MultipartFile file, long id) throws Exception {
		
		
		ResponseUpdateProduct product = mapper.readValue(updateProduct, ResponseUpdateProduct.class);
		
		UserAccount useraccount = useraccountRepo.findById(product.getUserId())
				.orElseThrow(() -> new IdNotFoundException("userId Not Found"+product.getUserId()));
		
		String userId = useraccount.getuserRoll();
		 	 
		if(userId.equalsIgnoreCase("admin")) {
		

		Product findById = productRepo.findById(id).orElseThrow(() -> new IdNotFoundException("id not found"));
		
			findById.setProductName(product.getProductName());
		
			Category category = categoryRepo.findById(product.getCategoryId())
					.orElseThrow(() -> new IdNotFoundException("categoryId not found"+product.getCategoryId()));
			 
			findById.setCategory(category);	 
			
			
			findById.setStockDetails(product.getStockDetails());
			
			
			if(file !=null) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());

			ImageProduct img = imageRepo.findById(product.getImageId())
					.orElseThrow(() -> new IdNotFoundException("imageId not found"+ product.getImageId()));
			 		
			img.setImageName(fileName);
			img.setImageType(file.getContentType());
			img.setImageData(file.getBytes());

			img.setProduct(findById);
			imageRepo.save(img);
			}
			

			return productRepo.save(findById);
		}
		
		else {
			throw new ProductCustomException("You are not updated in this product"+userId);
		}
	}

	public Optional<Product> getProductById(long productId) {
		Optional<Product> product;
		try {
			product = productRepo.findById(productId);
		}

		catch (Exception e) {
			throw new ProductNotFoundException("Product Not found" + productId + e.getMessage());
		}

		return product;
	}

	 

	public List<Product> filterbyId(String pName) {
		List<Product> findByfilterproduct = null;
		try {
			
			findByfilterproduct = productRepo.findByfilterproduct(pName);
		} catch (Exception e) {
			throw new ProductCustomException(e.getMessage());
		}

		return findByfilterproduct;

	}

}
