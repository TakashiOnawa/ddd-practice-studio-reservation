package org.taonaw.reservation.domain.model.member;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class MemberId {
    public static final MemberId NON_MEMBER_ID;

    private final String value;

    public MemberId(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "Member id is required.");
        this.value = value;
    }

    public boolean isNonMember() {
        return value.isEmpty();
    }

    private MemberId() {
        value = "";
    }

    static {
        NON_MEMBER_ID = new MemberId();
    }
}
