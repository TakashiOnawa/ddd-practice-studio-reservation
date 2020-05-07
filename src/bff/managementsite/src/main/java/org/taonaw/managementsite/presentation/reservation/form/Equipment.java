package org.taonaw.managementsite.presentation.reservation.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.taonaw.managementsite.application.facilitymanagement.query.EquipmentDto;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipment {
    private String id;
    private String name;
    private String categoryName;
    private int quantity;
    private boolean outOfStocks;

    public List<String> getIds() {
        var ids = new ArrayList<String>();
        for (var i = 0; i < quantity; i++) {
            ids.add(id);
        }
        return ids;
    }

    public static Equipment from(EquipmentDto equipmentDto) {
        var equipment = new Equipment();
        equipment.setId(equipmentDto.getEquipmentId());
        equipment.setCategoryName(equipmentDto.getCategoryName());
        equipment.setName(equipmentDto.getName());
        equipment.setQuantity(0);
        return equipment;
    }
}
