package org.taonaw.reservation.domain.model.equipment;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class EquipmentId {
    @NonNull
    private final String value;
}
