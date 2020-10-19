package org.taonaw.studio_reservation.domain.model.studio;

import de.huxhorn.sulky.ulid.ULID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class StudioId {
    private final String value;

    public StudioId(String value) {
        Assertion.required(value);
        this.value = value;
    }

    public static StudioId newId() {
        return new StudioId(new ULID().nextULID());
    }
}
