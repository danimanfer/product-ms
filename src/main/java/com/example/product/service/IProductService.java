package com.example.product.service;

import java.util.List;
import java.util.Optional;

import com.example.product.model.Product;

public interface IProductService {
	
	
	Product save(Product  product);
	Optional<Product> findById(Long id);
	Optional<Product> delete(Long id);
	Product update(Product product);
	List<Product> listAll();
	List<Product> search( Double min_price ,
			Double max_price,
			String q);

}
