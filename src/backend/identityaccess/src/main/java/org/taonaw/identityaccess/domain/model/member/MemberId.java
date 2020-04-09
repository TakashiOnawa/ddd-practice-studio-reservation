package org.taonaw.identityaccess.domain.model.member;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.identityaccess.domain.shared.Assertion;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class MemberId {
    private final String value;

    public static MemberId newId() {
        return new MemberId(UUID.randomUUID().toString());
    }

    public MemberId(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "Member id is required.");
        this.value = value;
    }
}
