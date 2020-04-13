package org.taonaw.reservation.infrastructure.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestOperations;
import org.taonaw.reservation.domain.model.equipment.Equipment;
import org.taonaw.reservation.domain.model.equipment.EquipmentId;
import org.taonaw.reservation.domain.model.equipment.EquipmentStocks;
import org.taonaw.reservation.domain.model.equipment.IEquipmentRepository;

import java.util.Objects;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class EquipmentRepository implements IEquipmentRepository {
    @Autowired
    @Qualifier("facilityManagementRestOptions")
    private final RestOperations facilityManagementRestOptions;

    @Override
    public Optional<Equipment> findBy(@NonNull EquipmentId equipmentId) {
        var uri = "/equipments/{equipmentId}";
        var response = facilityManagementRestOptions
                .getForEntity(uri, EquipmentDto.class, equipmentId.getValue());
        if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            return Optional.empty();
        }
        var dto = Objects.requireNonNull(response.getBody());
        return Optional.of(new Equipment(new EquipmentId(dto.equipmentId), new EquipmentStocks(dto.stocks)));
    }

    @Getter
    @AllArgsConstructor
    private static class EquipmentDto {
        private String equipmentId;
        private String name;
        private int stocks;
        private String categoryId;
        private String categoryName;
    }
}
