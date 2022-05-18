package com.moldovan.uni.bookingsystem.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final Collection<VendorExtension> VENDOR_EXTENSION = new LinkedList();
    private static final Contact CONTACT = new Contact("bookingsystem", "http://bookingsystem", "bookingsystem@awbd");
    public static final ApiInfo API_INFO = new ApiInfo("Booking System Documentation", "API documentation for the booking system", "1.0", "urn:tos", CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", VENDOR_EXTENSION);
    private static final List<String> ACCEPT_TYPES = Arrays.asList("application/json");
    private static final Set<String> PRODUCES = new HashSet<String>(ACCEPT_TYPES);

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(API_INFO).produces(PRODUCES);
    }
}