package com.olvera.payment.config

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun springOpenApi(): OpenAPI {
        return OpenAPI()
            .addServersItem(Server().url("/"))
            .info(
                Info().title("WarehouseOrders-backend")
                    .description("Experience API for the warehouse module")
                    .version("0.0.1")
            )
            .externalDocs(
                ExternalDocumentation()
                    .description("springdoc-openapi")
                    .url("http://springdoc.org")
            )
    }
}