package org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.shared.TimeRange;

import java.time.LocalTime;

@Getter
@EqualsAndHashCode(callSuper = true)
public class TimeRangeCondition extends UsageFeeCondition {
    private final TimeRange timeRange;

    public TimeRangeCondition(LocalTime startTime, LocalTime endTime) {
        timeRange = new TimeRange(startTime, endTime);

        if (timeRange.hasStartTimeSeconds()) {
            throw new IllegalArgumentException("開始時間に秒の指定はできません。");
        }
        if (timeRange.hasEndTimeSeconds()) {
            throw new IllegalArgumentException("終了時間に秒の指定はできません。");
        }
    }

    @Override
    public boolean isDuplicated(@NonNull UsageFeeCondition other) {
        if (super.isDuplicated(other)) return true;
        if (other.getClass() != this.getClass()) return false;
        return timeRange.isOverlapped(((TimeRangeCondition)other).timeRange);
    }
}
