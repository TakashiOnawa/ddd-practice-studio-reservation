package org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition;

import lombok.NonNull;

public enum UsageFeeConditionType {
    STUDIO(1, StudioCondition.class),
    DAY_TYPE(2, DayTypeCondition.class),
    TIME_RANGE(3, TimeRangeCondition.class),
    USER_COUNT(4, UserCountCondition.class);

    private final int value;
    private final Class<? extends UsageFeeCondition> conditionClassType;

    UsageFeeConditionType(int value, Class<? extends UsageFeeCondition> conditionClassType) {
        this.value = value;
        this.conditionClassType = conditionClassType;
    }

    public static UsageFeeConditionType of(@NonNull UsageFeeCondition usageFeeCondition) {
        for (var item : values()) {
            if (item.conditionClassType == usageFeeCondition.getClass()) {
                return item;
            }
        }
        throw new IllegalArgumentException("定義されていません。");
    }
}
