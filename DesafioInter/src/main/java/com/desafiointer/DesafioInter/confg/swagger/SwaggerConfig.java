package com.desafiointer.DesafioInter.confg.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.desafiointer.DesafioInter.models.Usuario;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
		public Docket empresaApi() {
			return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.desafiointer.DesafioInter"))
					.paths(PathSelectors.ant("/**"))
					.build()
					.ignoredParameterTypes(Usuario.class)
					.apiInfo(metaInfo())
					.globalOperationParameters(Arrays.asList(														
							new ParameterBuilder()
							.name("Authorization")
							.description("Header para token JWT")
							.modelRef(new ModelRef("string"))
							.parameterType("header")
							.required(false)
							.build()));
	}
	
	private ApiInfo metaInfo() {
		return new ApiInfo(
			"Desafio Inter - API REST",
			"API REST diversificação investimentos.",
			"1.0",
			"Terms of Service",
			new Contact("Lucas Fernando de Oliveira", null,"lucas.fer.oliveira@gmail.com"),
			null,
			null,new ArrayList<>()
			);	
	}
	
}
