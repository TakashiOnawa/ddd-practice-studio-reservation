package org.taonaw.reservation.domain.model.reservations;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PracticeTypes {
    BAND(1),
    PERSONAL(2);

    private final int value;

    public static PracticeTypes of(int value) {
        for (PracticeTypes practiceType : PracticeTypes.values()) {
            if (practiceType.value == value) {
                return practiceType;
            }
        }

        throw new IllegalArgumentException();
    }
}
