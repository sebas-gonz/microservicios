package com.detalle_boleta_perfulandia.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("API 2025 Gestion del detalle de las boletas").version("0.1")
				.description("Documentacion de la API para la gestion del detalle de las boletas."));
	}
}
