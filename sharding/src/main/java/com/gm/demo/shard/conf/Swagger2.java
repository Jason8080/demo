package com.gm.demo.shard.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Jason
 */
@Configuration
public class Swagger2 {
    @Bean
    public Docket vipApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("client")
                .genericModelSubstitutes(DeferredResult.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gm.demo.shard.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(vipApiInfo());
    }
    @Bean
    public Docket allyApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("server")
                .genericModelSubstitutes(DeferredResult.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gm.demo.shard.controller"))
                //过滤的接口
                .paths(PathSelectors.any())
                .build()
                .apiInfo(allyApiInfo());
    }

    private ApiInfo vipApiInfo() {
        return new ApiInfoBuilder()
                .title("在 线 文 档")
                .description("客户端实时接口文档")
                .version("1.0")
                .build();
    }

    private ApiInfo allyApiInfo() {
        return new ApiInfoBuilder()
                .title("在 线 文 档")
                .description("服务端实时接口文档")
                .version("1.0")
                .build();
    }
}