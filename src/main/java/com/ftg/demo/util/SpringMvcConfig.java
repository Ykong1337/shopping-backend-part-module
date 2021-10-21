package com.ftg.demo.util;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
@EnableSwagger2
@ComponentScan("com.ftg.demo.controller")
public class SpringMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fjc = new FastJsonHttpMessageConverter();
        FastJsonConfig fj = new FastJsonConfig();
        fj.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
        fjc.setFastJsonConfig(fj);
        converters.add(fjc);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter charset = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        charset.setWriteAcceptCharset(false);
        converters.add(charset);
    }

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("....");
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket api() {
        System.out.println("启动类");
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("开放接口API")
                .description("HTTP对外开放接口")
                .version("1.0.0")
                .termsOfServiceUrl("https://example.org/api/v2/api-docs")
                .license("LICENSE")
                .licenseUrl("https://example.org/api/v2/api-docs")
                .build();
    }
}
