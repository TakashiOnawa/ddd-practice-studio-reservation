package org.taonaw.facilitymanagement.application.register_equipment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class RegisterEquipmentCommand {
    @NonNull private String name;
    private int stocks;
    @NonNull private String categoryId;
}
