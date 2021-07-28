package com.example.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.controller.form.UpdateProductForm;
import com.example.product.model.Product;
import com.example.product.service.IProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private  IProductService productService;
	
	
	@ApiOperation(value = "Inserção de um produto")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Produto inserido com Sucesso."),
			@ApiResponse(code = 400, message = "Erro de validação.")
	})

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Product save( @RequestBody @Valid Product product) {
		
		return productService.save(product);
		
		
	}
	
	   @ApiOperation(value = "Realiza a atualização de um produto existente")
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Retorna 200 para produto atualizado com sucesso"),
	            @ApiResponse(code = 400, message = "Retorna 400 para algum erro de validação."),
	            @ApiResponse(code = 404, message = "Retorna 404 para produto não existente.")
	    })
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable Long id,@RequestBody @Valid UpdateProductForm form) {
		
		return productService.findById(id).map( product -> {
			product.setName(form.getName());
			product.setDescription(form.getDescription());
			product.setPrice(form.getPrice());
			product = productService.update(product);
			return ResponseEntity.ok().body(product);

		}) .orElse( ResponseEntity.notFound().build());
	}

	   
	    @ApiOperation(value = "Exibe os dados de um produto por id")
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Retorna 200 para produto existente e retorna o produto"),
	            @ApiResponse(code = 404, message = "Retorna 404 para produto não existente.")
	    })
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> listById(@PathVariable Long id){

		return productService.findById(id).map( productVerify -> {

			return ResponseEntity.ok().body(productVerify);
			}).orElse( ResponseEntity.notFound().build());
	}
	
	    @ApiOperation(value = "Exibe os dados de todos os produtos existentes.")
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Retorna 200 para todos os casos mesmo se não houver produto.")
	    })
	@GetMapping
	public List<Product> listAll(){
		
		return productService.listAll();
	}
	

	    @ApiOperation(value = "Consulta de produtos pelos parâmetros: 'q' igual o name ou description, 'min_price' maior igual(>=) o valor mínimo de price e 'max_price' menor igual (<=)o valor máximo de price .")
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Retorna 200 para todos os casos mesmo se não houver produto.")
	    })
	    
	@GetMapping("/search")
	public List<Product>  listParam(@RequestParam(value ="min_price" ,required  = false) Double min_price, @RequestParam(value = "max_price", required  = false) Double max_price, @RequestParam(value ="q",required = false) String q) {
	
		return productService.search(min_price,max_price, q);
		
		
	}
	
	  @ApiOperation(value = "Exclusão de um produto por id")
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Retorna 200 para produto excluído com sucesso."),
	            @ApiResponse(code = 404, message = "Retorna 404 para produto não existente.")
	    })

	@DeleteMapping("/{id}")
	public ResponseEntity<Object>  delete(@PathVariable Long id){
		
	return productService.delete(id).map(productVerify ->{
			 return ResponseEntity.ok().build();
					
			}).orElse( ResponseEntity.notFound().build());
	}
	

}
