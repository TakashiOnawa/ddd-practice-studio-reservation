package org.taonaw.managementsite.application.facilitymanagement.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EquipmentDto {
    private String equipmentId;
    private String name;
    private int stocks;
    private String categoryId;
    private String categoryName;
}
