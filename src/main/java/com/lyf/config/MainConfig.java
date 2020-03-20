package com.lyf.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@SpringBootConfiguration
public class MainConfig implements WebMvcConfigurer, WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建fastJson消息转换器
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();

        List<MediaType> medias = new ArrayList<>();
        medias.add(MediaType.TEXT_HTML);
        medias.add(MediaType.APPLICATION_JSON_UTF8);
        converter.setSupportedMediaTypes(medias);

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        converter.setFastJsonConfig(fastJsonConfig);

        //避免返回的json中文乱码
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        //将fastjson添加到消息转换器列表
        converters.add(converter);
    }

    //spring boot欢迎页
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        WebMvcConfigurer.super.addViewControllers(registry);

    }

    // spring boot错误页
    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/views/404.jsp"));
    }

    //    多文件上传
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        //30MB
        multipartResolver.setMaxUploadSize(31457280);
        multipartResolver.setDefaultEncoding("utf-8");
        return multipartResolver;
    }

    // kaptcha验证码
    @Bean
    public DefaultKaptcha defaultKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "yes");
        properties.setProperty("kaptcha.border.color", "30,144,255");
        properties.setProperty("kaptcha.textproducer.font.color", "blue");//文本颜色
        properties.setProperty("kaptcha.textproducer.font.size", "30");//文本大小
        properties.setProperty("kaptcha.textproducer.char.length", "4");//文本长度
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");//文本字体样式
        properties.setProperty("kaptcha.image.width", "110");//图片宽度
        properties.setProperty("kaptcha.image.height", "40");//图片高度
        properties.setProperty("kaptcha.session.key", "code");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    //    WebSocket配置,在打包时要注释,否则报错
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
