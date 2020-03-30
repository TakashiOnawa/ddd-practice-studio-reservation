package org.taonaw.facilitymanagement.infrastructure.query;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.taonaw.facilitymanagement.domain.model.equipment.Equipment;
import org.taonaw.facilitymanagement.domain.model.equipment.EquipmentId;
import org.taonaw.facilitymanagement.domain.model.equipment.IEquipmentRepository;
import org.taonaw.facilitymanagement.domain.model.equipmentcategory.EquipmentCategory;
import org.taonaw.facilitymanagement.domain.model.equipmentcategory.IEquipmentCategoryRepository;
import org.taonaw.facilitymanagement.query.equipment.EquipmentDto;
import org.taonaw.facilitymanagement.query.equipment.IEquipmentQuery;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class EquipmentQuery implements IEquipmentQuery {
    @Autowired
    private final IEquipmentRepository equipmentRepository;
    @Autowired
    private final IEquipmentCategoryRepository equipmentCategoryRepository;

    @Override
    public List<EquipmentDto> getAll() {
        var equipments = equipmentRepository.findAll();
        var equipmentCategories = equipmentCategoryRepository.findAll().stream()
                .collect(Collectors.toMap(EquipmentCategory::getEquipmentCategoryId, item -> item));

        return equipments.stream()
                .map(item -> create(item, equipmentCategories.get(item.getCategoryId())))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EquipmentDto> getByEquipmentId(@NonNull String equipmentId) {
        var res = equipmentRepository.findBy(new EquipmentId(equipmentId));
        if (res.isEmpty()) {
            return Optional.empty();
        }
        var equipment = res.get();
        var equipmentCategory = equipmentCategoryRepository.findBy(equipment.getCategoryId()).orElseThrow();
        return Optional.of(create(equipment, equipmentCategory));
    }

    private EquipmentDto create(Equipment equipment, EquipmentCategory equipmentCategory) {
        return EquipmentDto.builder()
                .equipmentId(equipment.getEquipmentId().getValue())
                .name(equipment.getName().getValue())
                .stocks(equipment.getStocks().getValue())
                .categoryId(equipment.getCategoryId().getValue())
                .categoryName(equipmentCategory.getName().getValue())
                .build();
    }
}
