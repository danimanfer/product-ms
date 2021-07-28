package com.example.product.specification;

import org.springframework.data.jpa.domain.Specification;

import com.example.product.model.Product;

public class ProductMaxPriceSpec {

	public static Specification<Product> maxPrice(Double maxPrice) {
		if (maxPrice == null){
			return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(true));
		} else {
			return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.lessThanOrEqualTo(root.<String>get("price"), maxPrice.toString());
		}

	}

}
