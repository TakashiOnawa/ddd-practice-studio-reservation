package org.taonaw.reservation.infrastructure.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestOperations;
import org.taonaw.reservation.domain.model.cancellationfeesetting.CancellationFeeRate;
import org.taonaw.reservation.domain.model.cancellationfeesetting.CancellationFeeSetting;
import org.taonaw.reservation.domain.model.cancellationfeesetting.ICancellationFeeSettingRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class CancellationFeeSettingRepository implements ICancellationFeeSettingRepository {
    @Autowired
    @Qualifier("facilityManagementRestOptions")
    private final RestOperations facilityManagementRestOptions;

    @Override
    public CancellationFeeSetting get() {
        var uri = "tenant_setting/cancellation_fee_rates";
        var response = facilityManagementRestOptions.exchange(uri, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CancellationFeeRateDto>>() {});
        var dtoList = Objects.requireNonNull(response.getBody());
        return new CancellationFeeSetting(dtoList.stream()
                        .map(item -> new CancellationFeeRate(item.daysAgo, item.rate))
                        .collect(Collectors.toSet()));
    }

    @Getter
    @AllArgsConstructor
    private static class CancellationFeeRateDto {
        private int daysAgo;
        private double rate;
    }
}
