package com.example.product.specification;

import org.springframework.data.jpa.domain.Specification;

import com.example.product.model.Product;

public class ProductMinPriceSpec {


	public static Specification<Product> minPrice(Double minPrice) {
		if (minPrice == null){
			return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(true));
		} else {
			return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.greaterThanOrEqualTo(root.<String>get("price"), minPrice.toString());
		}

	}

}
