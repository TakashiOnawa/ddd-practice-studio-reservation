package org.taonaw.studio_reservation.domain.model.reservation;

public enum ReservationStatus {
    RESERVED(1),
    CANCELED(2);

    private final int value;

    ReservationStatus(int value) {
        this.value = value;
    }
}
