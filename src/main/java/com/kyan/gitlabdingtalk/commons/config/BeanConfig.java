package com.kyan.gitlabdingtalk.commons.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Http Configuration
 *
 * @author kyan
 * @date 2019/4/26
 */
@Configuration
public class BeanConfig {

//    /**
//     * 使用@Bean注入FastJsonHttpMessageConverter
//     *
//     * @return
//     */
//    @Bean
//    public HttpMessageConverters httpMessageConverters() {
//        return new HttpMessageConverters((HttpMessageConverter<?>) fastJsonHttpMessageConverter());
//    }
//
//    /**
//     * 使用@Bean注入RestTemplate
//     *
//     * @param builder
//     * @return
//     */
//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        List<HttpMessageConverter<?>> converters = builder.build().getMessageConverters();
//        converters.add(0, fastJsonHttpMessageConverter());
//        return builder.build();
//    }
//
//    private FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//        return fastJsonHttpMessageConverter;
//    }
}
