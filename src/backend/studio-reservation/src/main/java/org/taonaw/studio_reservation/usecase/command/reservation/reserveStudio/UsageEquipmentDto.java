package org.taonaw.studio_reservation.usecase.command.reservation.reserveStudio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.equipment.EquipmentId;
import org.taonaw.studio_reservation.domain.model.reservation.usageEquipment.UsageEquipmentQuantity;

@Getter
@Builder
@AllArgsConstructor
public class UsageEquipmentDto {
    private EquipmentId equipmentId;
    private UsageEquipmentQuantity quantity;
}
