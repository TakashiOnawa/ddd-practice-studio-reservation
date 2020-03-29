package org.taonaw.facilitymanagement.domain.model.studio;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.facilitymanagement.domain.shared.Assertion;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class StudioId {
    private final String value;

    public static StudioId newId() {
        return new StudioId(UUID.randomUUID().toString());
    }

    public StudioId(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "Studio id is required.");
        this.value = value;
    }
}
