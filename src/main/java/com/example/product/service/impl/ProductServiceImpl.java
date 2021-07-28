package com.example.product.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import com.example.product.service.IProductService;
import com.example.product.specification.ProductMaxPriceSpec;
import com.example.product.specification.ProductMinPriceSpec;
import com.example.product.specification.ProductNameDescriptionSpec;


@Service
public class ProductServiceImpl implements IProductService{
	
	
	@Autowired
	private ProductRepository productRepository;
	


	@Override
	public Product save(Product product) {
		
		return productRepository.save(product);
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public Optional<Product> delete(Long id) {
		
		Optional<Product> productVerify = productRepository.findById(id);
		
		if (productVerify.isPresent()) {
			
			productRepository.deleteById(id);
		}
		
		return productVerify;
		
	}

	@Override
	public Product update(Product product) {

		return productRepository.save(product);
	}

	@Override
	public List<Product> listAll() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> search( Double min_price ,
	Double max_price,
	String q) {

		Specification<Product> specification = Specification
                .where(ProductNameDescriptionSpec.name(q))
                .and(ProductMinPriceSpec.minPrice(min_price))
                .and(ProductMaxPriceSpec.maxPrice(max_price));
		
		List<Product> list = productRepository.findAll(specification);
		
		
		return list;
		
	}

	
}
