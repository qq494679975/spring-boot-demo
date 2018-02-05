package demo.cwd.swagger.config;

import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

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
                        regex("/demo/.*")
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

    /**
     * 生成html文章专用
     *
     private static final String Doctor_API = "doctor";
     private static final String Patient_API = "patient";
     private static final String Other_API = "other";
     private static final String GateWay_API = "gateway";
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String groupName="demo";
        //String groupName="patient";
        //String groupName="other";
        // String groupName="gateway";

        URL remoteSwaggerFile = new URL("http://127.0.0.1:8080/v2/api-docs?group="+groupName);
        Path outputFile = Paths.get("spring-boot-swagger/build/"+groupName);

        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .withBasePathPrefix()
                .build();

        Swagger2MarkupConverter converter = Swagger2MarkupConverter.from(remoteSwaggerFile)
                .withConfig(config)
                .build();

        converter.toFile(outputFile);
    }
}