package org.taonaw.reservation.domain.model.members;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.reservation.domain.shared.Assertion;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class MemberId {

    private final String value;
    private boolean isNonMember;

    private MemberId(String value, boolean isNonMember) {
        Assertion.argumentNotEmpty(value, "Member id is required.");
        this.value = value;
        this.isNonMember = isNonMember;
    }

    public MemberId(@NonNull String value) {
        this(value, false);
    }

    public static MemberId nonMember() {
        return new MemberId(UUID.randomUUID().toString(), true);
    }
}
