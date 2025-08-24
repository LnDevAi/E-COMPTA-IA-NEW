package com.ecomptaia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.ecomptaia.entity")
@EnableJpaRepositories("com.ecomptaia.repository")
public class EComptaIaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EComptaIaApplication.class, args);
        System.out.println("🚀 E-COMPTA-IA INTERNATIONAL Backend démarré avec succès !");
        System.out.println("📊 Application disponible sur: http://localhost:8080");
        System.out.println("📚 Documentation API: http://localhost:8080/swagger-ui.html");
    }
}
