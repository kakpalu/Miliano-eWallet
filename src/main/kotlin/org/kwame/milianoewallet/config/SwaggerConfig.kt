package org.kwame.milianoewallet.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {
    @Bean
    fun springShopOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info().title("EWallet API Docs")
                    .description("MILANO EWALLET REST API documentation")
                    .version("v1.0.0")
            )
    }
}