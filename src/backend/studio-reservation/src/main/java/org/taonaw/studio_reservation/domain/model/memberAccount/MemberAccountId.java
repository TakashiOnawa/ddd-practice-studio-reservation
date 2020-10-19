package org.taonaw.studio_reservation.domain.model.memberAccount;

import de.huxhorn.sulky.ulid.ULID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class MemberAccountId {
    private final String value;

    public MemberAccountId(String value) {
        Assertion.required(value);
        this.value = value;
    }

    public static MemberAccountId newId() {
        return new MemberAccountId(new ULID().nextULID());
    }
}
