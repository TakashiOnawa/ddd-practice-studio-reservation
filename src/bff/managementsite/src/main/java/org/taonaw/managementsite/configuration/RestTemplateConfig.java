package org.taonaw.managementsite.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;

@Configuration
public class RestTemplateConfig {
    private final String reservationServiceUri = "http://reservation:8080";
    private final String identityAccessServiceUri = "http://identityaccess:8080";
    private final String facilityManagementServiceUri = "http://facilitymanagement:8080";

    @Bean
    @Qualifier("reservationRestOptions")
    public RestOperations reservationRestOptions(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.rootUri(reservationServiceUri).build();
    }

    @Bean
    @Qualifier("identityAccessRestOptions")
    public RestOperations identityAccessRestOptions(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.rootUri(identityAccessServiceUri).build();
    }

    @Bean
    @Qualifier("facilityManagementRestOptions")
    public RestOperations facilityManagementRestOptions(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.rootUri(facilityManagementServiceUri).build();
    }
}
