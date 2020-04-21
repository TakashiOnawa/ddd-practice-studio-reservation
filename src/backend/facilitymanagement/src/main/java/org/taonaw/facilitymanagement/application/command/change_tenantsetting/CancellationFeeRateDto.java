package org.taonaw.facilitymanagement.application.command.change_tenantsetting;

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
