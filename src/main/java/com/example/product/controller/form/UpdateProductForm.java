package com.example.product.controller.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateProductForm {
	
	
	@NotBlank(message = "Product cannot be blank")
	private String name;
	
	@NotBlank(message = "Description cannot be blank")
	private String description;
	
	@Min(value = 0, message = "Price cannot be negative")
	@NotNull(message = "Price cannot be Null")
	private Double price;


}
