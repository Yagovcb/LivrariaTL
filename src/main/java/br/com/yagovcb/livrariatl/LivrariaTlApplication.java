package br.com.yagovcb.livrariatl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;

@Cacheable
@SpringBootApplication
public class LivrariaTlApplication {

    public static void main(String[] args) {
        SpringApplication.run(LivrariaTlApplication.class, args);
    }

}
