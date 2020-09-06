package org.taonaw.studio_reservation.usecase.command.equipment.registerEquipmentCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.equipmentCategory.EquipmentCategoryName;

@Getter
@Builder
@AllArgsConstructor
public class RegisterEquipmentCategoryCommand {
    private final EquipmentCategoryName name;
}
