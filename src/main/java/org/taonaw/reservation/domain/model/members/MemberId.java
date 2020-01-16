package org.taonaw.reservation.domain.model.members;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.common.Assertion;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class MemberId {

    private final String value;
    private boolean isFixedMember;

    private MemberId(String value, boolean isFixedMember) {
        Assertion.argumentNotEmpty(value, "Member id is required.");
        this.value = value;
        this.isFixedMember = isFixedMember;
    }

    public MemberId(@NonNull String value) {
        this(value, false);
    }

    public static MemberId fixedMember() {
        return new MemberId(UUID.randomUUID().toString(), true);
    }
}
