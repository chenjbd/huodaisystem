package com.cc.app.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("后台API接口文档管理")
                .description("后台api接口文档，后台返回数据结构：{rtnCode:\"000000\",rtnMsg:\"\",data:{}}；" +
                        "rtnCode返回码，000000表示成功,其他为失败；data为返回的实际数据，为null表示查询不到数据")
                .termsOfServiceUrl("127.0.0.1:9000")
                .version("1.0")
                .build();
    }

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cc.app"))
                .paths(PathSelectors.any())//regex("/api/.*")
                .build();
    }
}
