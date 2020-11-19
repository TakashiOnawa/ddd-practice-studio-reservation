package org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition;

import org.taonaw.studio_reservation.domain.model.studio.StudioId;

import java.time.LocalDateTime;

public enum UsageFeeConditionType {
    STUDIO(1),
    DAY_TYPE(2),
    TIME_PERIOD(3),
    USER_COUNT(4);

    int value;

    UsageFeeConditionType(int value) {
        this.value = value;
    }

    public static UsageFeeCondition createStudioCondition(StudioId studioId) {
        return new StudioCondition(STUDIO, studioId);
    }

    public static UsageFeeCondition createDayTypeCondition(DayType dayType) {
        return new DayTypeCondition(DAY_TYPE, dayType);
    }

    public static UsageFeeCondition createTimePeriodCondition(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return new TimePeriodCondition(TIME_PERIOD, startDateTime, endDateTime);
    }

    public static UserCountCondition createUserCountCondition(int userCount) {
        return new UserCountCondition(USER_COUNT, userCount);
    }
}
