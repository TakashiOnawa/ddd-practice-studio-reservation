package org.taonaw.studio_reservation.domain.model.practiceTypeSetting;

public enum PracticeTypes {
    BAND(1),
    PERSONAL(2);

    private final int value;

    PracticeTypes(int value) {
        this.value = value;
    }

    public static PracticeTypes of(int value) {
        for (var item : values()) {
            if (item.value == value) {
                return item;
            }
        }
        throw new IllegalArgumentException("定義されていません。");
    }
}
