package org.taonaw.managementsite.application.error;

import java.util.List;
import java.util.stream.Collectors;

public class EquipmentOutOfStocksError extends ErrorInformation {
    private List<String> equipmentIds;

    public EquipmentOutOfStocksError(int code, String message, List<String> equipmentIds) {
        super(code, message);
        this.equipmentIds = equipmentIds;
    }

    public List<String> getEquipmentIds() {
        return equipmentIds.stream().collect(Collectors.toUnmodifiableList());
    }
}
