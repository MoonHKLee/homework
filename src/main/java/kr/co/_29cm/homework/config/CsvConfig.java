package kr.co._29cm.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class CsvConfig {

    @Bean
    public ResourceLoader resourceLoader() {
        return new DefaultResourceLoader();
    }
}
