package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;

/**
 * Description：swagger2配置类
 * @Author yangshilei
 * @Date 2019-04-29 00:05
 */
@Configuration
public class SwaggerConfig {
//
//    @Bean
//    public Docket myDocket() {
//        Docket docket = new Docket(DocumentationType.SWAGGER_2);
//        ApiInfo apiInfo = new ApiInfoBuilder()
//                .title("elasticsearch---Api接口文档") // 标题
//                .description("elasticsearch学习") // 描述
//                .contact(new Contact("", "", ""))
//                .version("1.0") // 版本号
//                .build();
//        docket.apiInfo(apiInfo);
//        //设置只生成被Api这个注解注解过的Ctrl类中有ApiOperation注解的api接口的文档
//        docket.select()
//                .apis(RequestHandlerSelectors.basePackage("org.example.controller"))
//                .paths(PathSelectors.any())
//                .build();
//        return docket;
//    }

}