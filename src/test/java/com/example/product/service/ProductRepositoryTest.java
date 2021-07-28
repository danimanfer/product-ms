package com.example.product.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import com.example.product.specification.ProductMaxPriceSpec;
import com.example.product.specification.ProductMinPriceSpec;
import com.example.product.specification.ProductNameDescriptionSpec;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProductRepositoryTest {

	@Autowired(required = true)
	private ProductRepository repository ;
	
	@Test
	@DisplayName("Salvar um produto.")

    void createProductTest(){
		
		Product product = Product.builder().id(1L).name("Product A").description("Product A").price(3.50).build();
		
		repository.save(product);
	    
		Integer countProduct = repository.findAll().size();
	    Assertions.assertEquals(1, countProduct);
    }
	
	
	@Test
	@DisplayName("Excluir um produto.")
	public void deleteProductTest() {
		Product product = Product.builder().id(1L).name("Product A").description("Product A").price(3.50).build();
		
		repository.save(product);
		
		 Integer countProduct = repository.findAll().size();
		 Assertions.assertEquals(1, countProduct);
        
        repository.deleteById(product.getId());
        
        countProduct = repository.findAll().size();
	    Assertions.assertEquals(0, countProduct);
	}
	
	
	@Test
	@DisplayName("Alteração de um produto.")
	public void updateProductTest() {
		
		Product productA = Product.builder().id(1L).name("Product A").description("Product A").price(3.50).build();
		Product productB = Product.builder().id(1L).name("Product B").description("Product B").price(1.50).build();
		
        repository.save(productA);
        
        repository.save(productB);
        
        Optional<Product> productUpdate = repository.findById(1L);
        
       Assertions.assertEquals(productUpdate.get(), productB);
	}
	
	
	
	@Test
	@DisplayName("Pesquisa de um produto com Parametros.")
    void searchProductTest(){
		
		Product productA = Product.builder().id(1L).name("Product A").description("Product A").price(3.50).build();
		Product productB = Product.builder().id(2L).name("Product B").description("Product B").price(1.50).build();
		
        
        repository.save(productA);
        
        repository.save(productB);
        
        Specification<Product> specification = Specification
                .where(ProductNameDescriptionSpec.name("Product A"))
                .and(ProductMinPriceSpec.minPrice(Double.valueOf("1.50")))
                .and(ProductMaxPriceSpec.maxPrice(Double.valueOf("3.50")));
        
		List<Product> listaFiltrada = repository.findAll(specification);
		
		Assertions.assertEquals(1, listaFiltrada.size());
    }

}
