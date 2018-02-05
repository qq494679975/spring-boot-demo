package demo.cwd.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;


@EnableSwagger2
@Configuration
public class SwaggerConfig {
    private static final String GateWay_API = "demo";

    @Bean
    public Docket gatewayAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(GateWay_API)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .paths(or(
                        regex("/.*")
                ))
                .build()
               // .globalOperationParameters(pars)
                .apiInfo(gatewayApiInfo());
    }

    private ApiInfo gatewayApiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "三师平台API",
                "对外部应用提供功能与数据接口。",
                "1.0",
                "No terms of service",
                "admin@jkzl.com",
                "The Apache License, Version 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0.html"
        );

        return apiInfo;
    }

}