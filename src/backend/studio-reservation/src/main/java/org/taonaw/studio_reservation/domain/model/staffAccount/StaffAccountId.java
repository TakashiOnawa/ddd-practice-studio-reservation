package org.taonaw.studio_reservation.domain.model.staffAccount;

import de.huxhorn.sulky.ulid.ULID;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
public class StaffAccountId {
    private final String value;

    public StaffAccountId(String value) {
        Assertion.required(value);
        this.value = value;
    }

    public static StaffAccountId newId() {
        return new StaffAccountId(new ULID().nextULID());
    }
}
