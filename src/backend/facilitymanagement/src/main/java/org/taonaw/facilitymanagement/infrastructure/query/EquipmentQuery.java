package org.taonaw.facilitymanagement.infrastructure.query;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.facilitymanagement.domain.model.equipment.EquipmentId;
import org.taonaw.facilitymanagement.domain.model.equipment.IEquipmentRepository;
import org.taonaw.facilitymanagement.query.equipment.EquipmentDto;
import org.taonaw.facilitymanagement.query.equipment.IEquipmentQuery;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class EquipmentQuery implements IEquipmentQuery {
    private final IEquipmentRepository equipmentRepository;

    @Override
    public Optional<EquipmentDto> getByEquipmentId(@NonNull String equipmentId) {
        var res = equipmentRepository.findBy(new EquipmentId(equipmentId));
        if (res.isEmpty()) {
            return Optional.empty();
        }
        var equipment = res.get();
        return Optional.of(EquipmentDto.builder()
                .equipmentId(equipment.getEquipmentId().getValue())
                .name(equipment.getName().getValue())
                .categoryId(equipment.getCategoryId().getValue())
                .stocks(equipment.getStocks().getValue())
                .build());
    }
}
