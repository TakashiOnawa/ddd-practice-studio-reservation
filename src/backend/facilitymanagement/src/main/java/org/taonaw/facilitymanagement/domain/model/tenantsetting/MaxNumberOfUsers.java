package org.taonaw.facilitymanagement.domain.model.tenantsetting;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.facilitymanagement.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class MaxNumberOfUsers {
    private final int value;

    public static MaxNumberOfUsers UN_LIMITED = new MaxNumberOfUsers(Integer.MAX_VALUE);

    public MaxNumberOfUsers(int value) {
        Assertion.argumentMin(value, 1);
        this.value = value;
    }

    public boolean isUnLimited() {
        return value == Integer.MAX_VALUE;
    }
}
