package com.samgoldsee.movie.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("奶龙影视")
                        .description("奶龙影视网站的接口文档")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("SamGoldSee")
                                .url("https://github.com/Sam-Gold-See")
                                .email("chunxin.huang@m.scnu.edu.cn"))
                );
    }
}

