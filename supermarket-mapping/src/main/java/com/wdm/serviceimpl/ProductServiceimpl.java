package com.wdm.serviceimpl;

import java.io.IOException;
 
import java.util.Base64;
import java.util.List;
import java.util.Optional;
 
import java.util.stream.Collectors;
 
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
 
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wdm.controller.ProductController;
import com.wdm.entity.Category;
import com.wdm.entity.ImageProduct;
import com.wdm.entity.Product;
import com.wdm.exception.IdNotFoundException;
import com.wdm.exception.InvalidDataException;
import com.wdm.exception.ProductCustomException;
import com.wdm.exception.ProductNotFoundException;
import com.wdm.model.RequestProduct;
import com.wdm.repository.ImageProductRepository;
import com.wdm.repository.ProductMappingRespository;
import com.wdm.response.ProductResponse;
import com.wdm.service.ProductService;

@Service

public class ProductServiceimpl implements ProductService {

	
	private static final Logger logger = LogManager.getLogger(ProductServiceimpl.class);
	@Autowired
	ProductMappingRespository productRepo;

	@Autowired
	ImageProductRepository imageRepo;

	@Transactional
	public Product saveProduct(String requestProduct, MultipartFile file) throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		try {

			RequestProduct product = mapper.readValue(requestProduct, RequestProduct.class);

			Product product1 = new Product();

			product1.setProductName(product.getProductName());

			product1.setStockDetails(product.getStockDetails());
			
			Category cat = new Category();
			
			cat.setCategoryName(product.getCategory());
			
			product1.setCategory(cat);

			
			
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());

			ImageProduct img = new ImageProduct();
			img.setImageName(fileName);
			img.setImageType(file.getContentType());
			img.setImageData(file.getBytes());
//			Base64.getEncoder().encodeToString(file.getBytes());
//			System.out.println(Base64.getEncoder().encodeToString(file.getBytes()));

			img.setProduct(product1);
			imageRepo.save(img);

			return productRepo.save(product1);

		} catch (Exception e) {
			throw new ProductCustomException("Invalid" + e.getMessage());
		}
	}

	public void deletebyId(long id) {

		productRepo.findById(id).orElseThrow(() -> new ProductCustomException("Not Found"));

		productRepo.deleteById(id);

	}

	public List<Product> getAllproduct() {

		Product productImage = productRepo.findAll().get(0);
		List<String> collect = productImage.getProductImage().stream()
				.map(e -> "data:image/png;base64,/" + Base64.getEncoder().encodeToString(e.getImageData()))
				.collect(Collectors.toList());

		logger.info("save new product 	file={} ", collect);
		 

		return productRepo.findAll();

	}

	public Product updateProduct(RequestProduct product, long id) {

		Optional<Product> findById = productRepo.findById(id);
		Product p = new Product();
		if (findById.isPresent()) {
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
