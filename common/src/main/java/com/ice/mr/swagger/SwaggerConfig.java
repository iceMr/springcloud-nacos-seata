package com.ice.mr.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2).
				useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ice.mr.controller"))
				.paths(PathSelectors.regex("^(?!auth).*$"))
				.build()
				.securitySchemes(securitySchemes())
				.securityContexts(securityContexts())
				;
	}
	private List<ApiKey> securitySchemes() {
		ApiKey apiKey =  new ApiKey("Authorization", "Authorization", "header");
		List<ApiKey> list =new ArrayList<>();
		list.add(apiKey);
		return list;
	}
	private List<SecurityContext> securityContexts() {
		SecurityContext  securityContext  =	SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex("^(?!auth).*$"))
				.build();
		List<SecurityContext> list =new ArrayList<>();
		list.add(securityContext);
		return list;
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope =new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopeArray ={authorizationScope};
		SecurityReference   securityReference = new SecurityReference("Authorization", authorizationScopeArray);
		List<SecurityReference> list =new ArrayList<>();
		list.add(securityReference);
		return list;
	}
}
