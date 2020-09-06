package org.taonaw.studio_reservation.domain.model.memberAccount;

import de.huxhorn.sulky.ulid.ULID;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class MemberAccountId {
    private final String value;

    public MemberAccountId(@NonNull String value) {
        this.value = value;
    }

    public static MemberAccountId newId() {
        return new MemberAccountId(new ULID().nextULID());
    }
}
