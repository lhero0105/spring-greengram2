package com.green.greengram2_1.common;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// 스프링에노테이션, 뭔가 설정하며 빈등록까지 됩니다.
public class SwaggerConfig {
    // 클래스 명은 작명입니다.
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
        // 아래 매소드 호출
    }

    private Info apiInfo(){
        return new Info()
                .title("Greengram Ver.2")
                .description("인스타그램 클론 코딩")
                .version("2.0.0");
    }
}
