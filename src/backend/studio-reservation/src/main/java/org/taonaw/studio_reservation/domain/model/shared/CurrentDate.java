package org.taonaw.studio_reservation.domain.model.shared;

import java.time.LocalDateTime;

public class CurrentDate {
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
