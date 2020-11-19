package org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.shared.Assertion;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@EqualsAndHashCode(callSuper = true)
public class TimePeriodCondition extends UsageFeeCondition {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    public TimePeriodCondition(UsageFeeConditionType type, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(type);
        Assertion.required(startDateTime);
        Assertion.required(endDateTime);

        if (startDateTime.getSecond() != 0 || startDateTime.getNano() != 0) {
            throw new IllegalArgumentException("開始日時に秒の指定はできません。");
        }
        if (endDateTime.getSecond() != 0 || endDateTime.getNano() != 0) {
            throw new IllegalArgumentException("終了日時に秒の指定はできません。");
        }
        if (!startDateTime.isBefore(endDateTime)) {
            throw new IllegalArgumentException("終了日時が開始日時以前です。");
        }
        if (ChronoUnit.MINUTES.between(startDateTime, endDateTime) % 60 != 0) {
            throw new IllegalArgumentException("1 時間単位でなければなりません。");
        }

        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public boolean isDuplicated(@NonNull UsageFeeCondition other) {
        if (super.isDuplicated(other)) return true;
        if (other.getClass() != this.getClass()) return false;
        return isOverlapped((TimePeriodCondition)other);
    }

    public boolean isOverlapped(@NonNull TimePeriodCondition other) {
        return startDateTime.isBefore(other.endDateTime) && other.startDateTime.isBefore(endDateTime);
    }
}
