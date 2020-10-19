package org.taonaw.studio_reservation.shared;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CurrentDate {
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
