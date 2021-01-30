package cl.libro.startertest.generadorpdfstarter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.thymeleaf.spring5.SpringTemplateEngine;

import cl.libro.startertest.services.GeneradorPdf;

@Configuration
public class GeneradorPdfConfiguration {

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public GeneradorPdf mineducGeneradorPdfHelper(ResourceLoader resourceLoader) {
        return new GeneradorPdf(templateEngine(), resourceLoader);
    }    
}