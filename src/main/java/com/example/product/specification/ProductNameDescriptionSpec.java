package com.example.product.specification;

import org.springframework.data.jpa.domain.Specification;

import com.example.product.model.Product;

public class ProductNameDescriptionSpec {

	public static Specification<Product> name(String name) {
		if (name == null){
			return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        } else {
        return (root, criteriaQuery, criteriaBuilder) ->
        criteriaBuilder.or(
                criteriaBuilder.like(root.<String>get("name"), "%" + name + "%"),
                criteriaBuilder.like(root.<String>get("description"), "%" + name + "%")
        );
        }
    }
}
