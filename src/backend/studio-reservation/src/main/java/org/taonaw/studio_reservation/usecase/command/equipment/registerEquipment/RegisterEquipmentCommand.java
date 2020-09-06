package org.taonaw.studio_reservation.usecase.command.equipment.registerEquipment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.equipment.EquipmentName;
import org.taonaw.studio_reservation.domain.model.equipment.EquipmentStockCount;
import org.taonaw.studio_reservation.domain.model.equipmentCategory.EquipmentCategoryId;

@Getter
@Builder
@AllArgsConstructor
public class RegisterEquipmentCommand {
    private EquipmentName name;
    private EquipmentCategoryId categoryId;
    private EquipmentStockCount stockCount;
}
