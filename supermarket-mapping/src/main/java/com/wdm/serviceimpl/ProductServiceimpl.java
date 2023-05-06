package com.wdm.serviceimpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@Service
@CrossOrigin
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

		RequestProduct product = mapper.readValue(requestProduct, RequestProduct.class);

		try {

			UserAccount useraccount = useraccountRepo.findById(product.getUserId())
					.orElseThrow(() -> new IdNotFoundException("userId Not Found"));

			Category category = categoryRepo.findById(product.getCategoryId())
					.orElseThrow(() -> new IdNotFoundException("categoryId not found " + product.getCategoryId()));

			String userId = useraccount.getUserRole();

			if (userId.equalsIgnoreCase("admin")) {
				Product product1 = new Product();

				product1.setProductName(product.getProductName());

				product1.setStocks(product.getStock());

				product1.setUnit(product.getUnit());

				product1.setPrice(product.getPrice());

				product1.setAddedAt(LocalDateTime.now());

				product1.setCategory(category);

				String fileName = StringUtils.cleanPath(file.getOriginalFilename());

				ImageProduct img = new ImageProduct();
				img.setImageName(fileName);
				img.setImageType(file.getContentType());
				img.setImageData(file.getBytes());

				product1.setProductImage(img);
				return productRepo.save(product1);
			}

			else {
				throw new ProductCustomException("you are not admin can't add product" + userId);
			}

		} catch (DataIntegrityViolationException e) {

			throw new IllegalArgumentException(product.getProductName() + " already exists");
		} catch (Exception e) {
			throw new ProductCustomException(e.getMessage());
		}

	}

	public void deletebyId(long id) {

		productRepo.findById(id).orElseThrow(() -> new ProductCustomException("Not Found"));

		productRepo.deleteById(id);

	}

	public List<ProductResponse> getAllproduct() {

		try {

			List<ProductResponse> collect = productRepo.findAllOrderByAddedAtDesc().stream()
					.map(e -> new ProductResponse(e.getProductId(), e.getProductName(), e.getStocks(), e.getUnit(),
							e.getCategory().getCategoryId(), e.getCategory().getCategoryName(), e.getPrice(),
							e.getProductImage().getImageData(), e.getProductImage().getImageId()))
					.collect(Collectors.toList());

			return collect;

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}

	}

	public Product updateProduct(String updateProduct, MultipartFile file, long id) throws Exception {

		ResponseUpdateProduct product = mapper.readValue(updateProduct, ResponseUpdateProduct.class);

		UserAccount useraccount = useraccountRepo.findById(product.getUserId())
				.orElseThrow(() -> new IdNotFoundException("userId Not Found" + product.getUserId()));

		String userId = useraccount.getUserRole();

		if (userId.equalsIgnoreCase("admin")) {

			Product findById = productRepo.findById(id).orElseThrow(() -> new IdNotFoundException("id not found"));

			findById.setProductName(product.getProductName());

			Category category = categoryRepo.findById(product.getCategoryId())
					.orElseThrow(() -> new IdNotFoundException("categoryId not found" + product.getCategoryId()));

			findById.setCategory(category);

			findById.setStocks(product.getStock());

			findById.setUnit(product.getUnit());
			findById.setPrice(product.getPrice());

			if (file != null) {
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());

				ImageProduct img = imageRepo.findById(product.getImageId())
						.orElseThrow(() -> new IdNotFoundException("imageId not found" + product.getImageId()));

				img.setImageName(fileName);
				img.setImageType(file.getContentType());
				img.setImageData(file.getBytes());

				findById.setProductImage(img);

			}

			return productRepo.save(findById);
		}

		else {
			throw new ProductCustomException("You are not updated in this product" + userId);
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

	public List<ProductResponse> filterbyId(String pName) {
		List<ProductResponse> findByfilterproduct = null;

		System.out.println("--------------------");

		try {

			if (pName.isEmpty()) {
				findByfilterproduct = productRepo.findAllOrderByAddedAtDesc().stream()
						.map(e -> new ProductResponse(e.getProductId(), e.getProductName(), e.getStocks(), e.getUnit(),
								e.getCategory().getCategoryId(), e.getCategory().getCategoryName(), e.getPrice(),
								e.getProductImage().getImageData(), e.getProductImage().getImageId()))
						.collect(Collectors.toList());

			} else {
				findByfilterproduct = productRepo.findByfilterproduct(pName).stream()
						.map(e -> new ProductResponse(e.getProductId(), e.getProductName(), e.getStocks(), e.getUnit(),
								e.getCategory().getCategoryId(), e.getCategory().getCategoryName(), e.getPrice(),
								e.getProductImage().getImageData(), e.getProductImage().getImageId()))
						.collect(Collectors.toList());

				System.out.println("===========================");
			}
		} catch (Exception e) {

			throw new ProductCustomException(e.getMessage());
		}

		return findByfilterproduct;

	}

	public List<ProductResponse> getBycategory(long categoryId) {

		try {

			List<ProductResponse> collect = productRepo.findBycategoryId(categoryId).stream()
					.map(e -> new ProductResponse(e.getProductId(), e.getProductName(), e.getStocks(), e.getUnit(),
							e.getCategory().getCategoryId(), e.getCategory().getCategoryName(), e.getPrice(),
							e.getProductImage().getImageData(), e.getProductImage().getImageId()))
					.collect(Collectors.toList());

			System.out.println(collect);
			return collect;

		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}

	}

}
