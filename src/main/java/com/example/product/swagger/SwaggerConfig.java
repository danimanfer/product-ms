package com.example.product.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	   @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	        		.groupName("product-ms")
					.select().apis(RequestHandlerSelectors.basePackage("com.example.product.controller"))
					.paths(PathSelectors.ant("/**")).build() // todos os caminhos disponíveis
					.useDefaultResponseMessages(false)
			        .apiInfo(apiInfo());
	   }
	   
		private ApiInfo apiInfo() {
			return new ApiInfoBuilder()
		            .title("product-ms")
		            .description("API para criar, alterar, consultar e excluir um produto e com a possibilidade de realizar a busca de produtos filtrando por name, description e price")
		            .build();
		}
}
