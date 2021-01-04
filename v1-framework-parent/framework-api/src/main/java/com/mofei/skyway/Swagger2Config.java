package com.mofei.skyway;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author mofei
 * @date 2019/2/26 11:58
 */
@EnableSwagger2
@Configuration
@ConditionalOnProperty(prefix = "skyway.switch", name = "swagger-open", havingValue = "true")
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {// 创建API基本信息

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .host("ip + /SKYWAY-GATEWAY")
                .select()
                // 扫描该包下的所有需要在Swagger中展示的API，@ApiIgnore注解标注的除外
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        //API标题
        return new ApiInfoBuilder().title("skywayx 系统")
                .description("``简要描述``：\n" +
                        "\n" +
                        "HTTP请求格式和参数、消息签名及返回格式及状态\n" +
                        "HTTP请求格式：\n" +
                        "``http://ip:port/SKYWAY-GATEWAY/URL``\n\n"
                )// API描述
                .termsOfServiceUrl("ip + /GATEWAY/ + client")
                .version("v1.0")
                .build();
    }
}
