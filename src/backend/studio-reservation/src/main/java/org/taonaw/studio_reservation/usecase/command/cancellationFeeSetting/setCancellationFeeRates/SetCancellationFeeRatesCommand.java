package org.taonaw.studio_reservation.usecase.command.cancellationFeeSetting.setCancellationFeeRates;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.cancellationFeeSetting.CancellationFeeRates;

@Getter
@Builder
@AllArgsConstructor
public class SetCancellationFeeRatesCommand {
    private CancellationFeeRates cancellationFeeRates;
}
