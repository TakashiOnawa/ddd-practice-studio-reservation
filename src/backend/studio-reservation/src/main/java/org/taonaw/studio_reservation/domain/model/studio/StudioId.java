package org.taonaw.studio_reservation.domain.model.studio;

import de.huxhorn.sulky.ulid.ULID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
public class StudioId {
    private final String value;

    public StudioId(@NonNull String value) {
        this.value = value;
    }

    public static StudioId newId() {
        return new StudioId(new ULID().nextULID());
    }
}
