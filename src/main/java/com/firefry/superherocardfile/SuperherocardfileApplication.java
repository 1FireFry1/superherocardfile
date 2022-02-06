package com.firefry.superherocardfile;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SuperherocardfileApplication {


	public static void main(String[] args) {
		SpringApplication.run(SuperherocardfileApplication.class, args);

	}


	@Bean
	public OpenAPI openApiConfig(){
		return new OpenAPI().info(apiInfo());
	}

	private Info apiInfo() {
		Info info = new Info();
		info.title("SuperheroCardFile API")
				.description("test Rest Api")
				.version("v1.0.0");
		return info;
	}

}
