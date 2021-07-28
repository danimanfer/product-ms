package com.example.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Product cannot be blank")
	@Column(nullable = false)
	private String name;
	
	@NotBlank(message = "Description cannot be blank")
	@Column(nullable = false)
	private String description;
	
	@Min(value = 0, message = "Price cannot be negative")
	@NotNull(message = "Price cannot be Null")
	@Column(nullable = false, precision = 10, scale = 2)
	private Double price;

	
}
