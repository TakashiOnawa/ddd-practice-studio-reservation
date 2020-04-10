package org.taonaw.reservation.infrastructure;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;

@Configuration
public class RestTemplateConfig {
    private final String facilityManagementServiceUri = "http://facilitymanagement:8080";
    private final String identityAccessServiceUri = "http://identityaccess:8080";

    @Bean
    @Qualifier("facilityManagementRestOptions")
    public RestOperations facilityManagementRestOptions(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.rootUri(facilityManagementServiceUri).build();
    }

    @Bean
    @Qualifier("identityAccessRestOptions")
    public RestOperations identityAccessRestOptions(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.rootUri(identityAccessServiceUri).build();
    }
}
