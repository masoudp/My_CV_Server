package com.mpakbaz.mycvserver.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

@Configuration
@ComponentScan(basePackages = "com.mpakbaz.mycvserver")
@PropertySource("classpath:app.properties")
public class ApplicationConfig {
    @Autowired
    Environment env;


    @Bean
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean fmConfigFactoryBean = new FreeMarkerConfigurationFactoryBean();
        fmConfigFactoryBean.setTemplateLoaderPath("/templates/");
        return fmConfigFactoryBean;
    }
}
