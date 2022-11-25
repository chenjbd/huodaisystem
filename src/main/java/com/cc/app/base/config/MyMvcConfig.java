package com.cc.app.base.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 配置MVC，在调试时，如果网关配置了跨域，则api无需配置跨域
 * Created by Administrator on 2018/9/6.
 */
@Configuration
public class MyMvcConfig {

    @Bean
    public WebMvcConfigurer  webMvcConfigurer(){
        return new WebMvcConfigurer() {

            //配置方法参数解析器
            @Override
            public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
                resolvers.add(new CurrentUserHandlerMethodArgumentResolver());
            }

            //配置拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new SignValidInterceptor()).addPathPatterns("/**");
            }

            //配置跨域
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedMethods("*")
//                        .allowedOrigins("*")
//                        .allowedHeaders("*")
//                        .allowCredentials(true)
//                        .maxAge(3600);
//            }

            @Override
            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
                //创建fastjson
                FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
                FastJsonConfig config = new FastJsonConfig();
                config.setSerializerFeatures(SerializerFeature.PrettyFormat,//是否需要格式化
                        SerializerFeature.DisableCircularReferenceDetect,
                        SerializerFeature.WriteMapNullValue,
                        SerializerFeature.WriteDateUseDateFormat
                        //SerializerFeature.WriteNullStringAsEmpty
                );
                fastConverter.setFastJsonConfig(config);
                converters.add(fastConverter);
            }
        };
    }
}
