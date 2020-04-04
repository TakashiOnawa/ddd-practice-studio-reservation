package org.taonaw.reservation.common;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class CurrentDate {
    public LocalDateTime now() {
        return LocalDateTime.now();
    }

    public boolean isBefore(LocalDateTime dateTime) {
        return now().isBefore(dateTime);
    }
}
