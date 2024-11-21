package com.xcode_software;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class XcodeSoftwareApplication {

    private static final Logger logger = LoggerFactory.getLogger(XcodeSoftwareApplication.class);

    public static void main(String[] args) {
        var context = SpringApplication.run(XcodeSoftwareApplication.class, args);
        logActiveProfiles(context.getEnvironment());
    }

    private static void logActiveProfiles(Environment environment) {
        String profiles = environment.getActiveProfiles().length > 0
                ? String.join(", ", environment.getActiveProfiles())
                : "default";
        logger.info("Application started with active profile: {}", profiles);
    }
}
