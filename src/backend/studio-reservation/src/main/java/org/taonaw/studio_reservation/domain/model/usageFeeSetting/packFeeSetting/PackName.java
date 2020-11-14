package org.taonaw.studio_reservation.domain.model.usageFeeSetting.packFeeSetting;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class PackName {
    private final String value;

    public PackName(String value) {
        Assertion.argumentRange(value, 1, 20);
        this.value = value;
    }
}
