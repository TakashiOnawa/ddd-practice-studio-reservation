package org.taonaw.reservation.domain.model.members;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.reservation.common.domain.Assertion;

@Getter
@EqualsAndHashCode
public class MemberId {

    private final String value;

    public MemberId(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "value is required.");
        this.value = value;
    }
}
