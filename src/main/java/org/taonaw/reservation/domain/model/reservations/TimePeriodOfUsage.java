package org.taonaw.reservation.domain.model.reservations;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

@Getter
@EqualsAndHashCode
public class TimePeriodOfUsage {
    private final Date start;
    private final Date end;

    public TimePeriodOfUsage(@NonNull Date startDate, @NonNull Date endDate) {
        // 秒以下を削る。
        this.start = DateUtils.truncate(startDate, Calendar.MINUTE);
        this.end = DateUtils.truncate(endDate, Calendar.MINUTE);

        Calendar startCalendar = Calendar.getInstance();
        if (start.compareTo(end) >= 0) {
            throw new IllegalArgumentException("終了時間は開始時間よりも遅くなければなりません。");
        }
    }

    public Date startDayOfYear() {
        return DateUtils.truncate(this.start, Calendar.HOUR);
    }

    public boolean isOverlapping(@NonNull TimePeriodOfUsage other) {
        return start.compareTo(other.end) < 0 && other.start.compareTo(end) < 0;
    }

    public long minutes() {
        long startTime = this.start.getTime();
        long endTime = this.end.getTime();
        return (endTime - startTime) / (1000 * 60);
    }
}