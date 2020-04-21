package org.taonaw.facilitymanagement.application.query.tenantsetting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CancellationFeeRateDto {
    private int daysAgo;
    private double rate;
}
