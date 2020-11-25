package vladyslav.shuhai.psyhology;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import vladyslav.shuhai.psyhology.service.FileStorageService;

import javax.annotation.Resource;
@Configuration
@SpringBootApplication
public class PsyhologyApplication implements CommandLineRunner {
    @Resource
    FileStorageService storageService;
    public PsyhologyApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(PsyhologyApplication.class, args);
    }
    @Override
    public void run(String... arg) throws Exception {
        storageService.deleteAll();
        storageService.init();
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("https://psyhology-site.herokuapp.com");
            }
        };
    }
}

