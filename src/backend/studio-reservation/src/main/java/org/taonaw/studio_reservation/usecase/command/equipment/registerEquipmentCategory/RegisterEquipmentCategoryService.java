package org.taonaw.studio_reservation.usecase.command.equipment.registerEquipmentCategory;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.taonaw.studio_reservation.domain.model.equipmentCategory.EquipmentCategory;
import org.taonaw.studio_reservation.domain.model.equipmentCategory.EquipmentCategoryRepository;

@AllArgsConstructor
public class RegisterEquipmentCategoryService {
    @Autowired
    private final EquipmentCategoryRepository equipmentCategoryRepository;

    public void handle(@NonNull RegisterEquipmentCategoryCommand command) {
        var equipmentCategory = EquipmentCategory.create(command.getName());

        equipmentCategoryRepository.add(equipmentCategory);
    }
}
