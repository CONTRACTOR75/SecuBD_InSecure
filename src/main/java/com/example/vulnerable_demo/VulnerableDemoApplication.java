// src/main/java/com/example/vulnerable_demo/VulnerableDemoApplication.java
package com.example.vulnerable_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent ;
import org.springframework.context.event.EventListener ;

@SpringBootApplication
public class VulnerableDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(VulnerableDemoApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void serverReady() {
        System.out.println(" ");
        System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println(" ");
        System.out.println("       SERVEUR EN VERSION VULNÉRABLE LANCE avec succes sur le port : http://localhost:8080 ");
        System.out.println(" ");
        System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════════╝");
        System.out.println(" ");
    }
}