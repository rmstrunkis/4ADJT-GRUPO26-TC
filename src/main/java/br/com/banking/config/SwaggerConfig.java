package br.com.banking.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Value("${info.app.name:Api Documentation}")
    private String apiName;

    @Value("${info.app.description:Api Documentation}")
    private String apiDescription;

    @Value("${info.build.version}")
    private String version;

    @Bean
    public Docket swaggerSpringfoxDocket() {

        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                .genericModelSubstitutes(ResponseEntity.class)
					.forCodeGeneration(true)
					.genericModelSubstitutes(ResponseEntity.class)
					.directModelSubstitute(LocalDate.class, String.class)
					.directModelSubstitute(LocalDateTime.class, String.class)
					.directModelSubstitute(LocalTime.class, String.class)
					.select()
						.apis(RequestHandlerSelectors.basePackage("br.com.banking.resource"))
						.paths(PathSelectors.any())
					.build()
					.pathMapping("/");
	    }
    
    @Bean
	public UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder()
		        .deepLinking(true)
		        .displayOperationId(false)
		        .defaultModelsExpandDepth(1)
		        .defaultModelExpandDepth(1)
		        .defaultModelRendering(ModelRendering.EXAMPLE)
		        .displayRequestDuration(true)
		        .docExpansion(DocExpansion.NONE)
		        .filter(false)
		        .maxDisplayedTags(null)
		        .operationsSorter(OperationsSorter.ALPHA)
		        .showExtensions(false)
		        .tagsSorter(TagsSorter.ALPHA)
		        .validatorUrl(null)
		        .build();
	}

    public ApiInfo apiInfo() {
         return new ApiInfoBuilder()
        		 	.title(apiName)
        		 	.description(apiDescription)
        		 	.version(version)
        		 	.build();
    }
}