package com.xcode_software.config;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class RestTemplateConfigTest {

    @Test
    void testRestTemplateBeanCreation() {
        // Arrange
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RestTemplateConfig.class);

        // Act
        RestTemplate restTemplate = context.getBean(RestTemplate.class);

        // Assert
        assertNotNull(restTemplate, "RestTemplate bean should not be null");
        assertTrue(restTemplate instanceof RestTemplate, "Bean should be an instance of RestTemplate");

        // Clean up
        context.close();
    }
}
