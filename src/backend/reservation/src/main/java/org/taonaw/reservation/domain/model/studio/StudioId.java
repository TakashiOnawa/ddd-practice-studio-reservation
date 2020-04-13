package org.taonaw.reservation.domain.model.studio;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class StudioId {
    @NonNull
    private final String value;
}
