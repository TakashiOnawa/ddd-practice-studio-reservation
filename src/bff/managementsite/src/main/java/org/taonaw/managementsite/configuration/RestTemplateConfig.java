package org.taonaw.managementsite.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;

@Configuration
public class RestTemplateConfig {

    private final String reservationServiceUri = "http://reservation:8080";
    private final String identityaccessServiceUri = "http://identityaccess:8080";

    @Bean
    @Qualifier("reservationRestOptions")
    public RestOperations reservationRestOptions(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.rootUri(reservationServiceUri).build();
    }

    @Bean
    @Qualifier("identityaccessRestOptions")
    public RestOperations identityaccessRestOptions(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.rootUri(identityaccessServiceUri).build();
    }
}
