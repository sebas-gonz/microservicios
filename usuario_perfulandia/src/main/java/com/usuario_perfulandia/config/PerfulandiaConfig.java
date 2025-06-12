package com.usuario_perfulandia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PerfulandiaConfig {
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
