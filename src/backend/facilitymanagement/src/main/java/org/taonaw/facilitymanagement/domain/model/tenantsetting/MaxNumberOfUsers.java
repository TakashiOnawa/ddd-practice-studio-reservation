package org.taonaw.facilitymanagement.domain.model.tenantsetting;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.facilitymanagement.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class MaxNumberOfUsers {
    private final int value;

    public MaxNumberOfUsers(int value) {
        Assertion.argumentMin(value, 1);
        this.value = value;
    }
}
