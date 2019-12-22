package org.taonaw.reservation.domain.model.reservations.specificatiions;

import lombok.NonNull;
import org.apache.commons.lang3.time.DateUtils;
import org.taonaw.reservation.domain.model.reservations.Reservation;
import org.taonaw.reservation.domain.model.reservations.TimePeriodOfUsage;

public class TimePeriodUnitOfUsageSpecification implements IReservationSpecification {

    @Override
    public boolean isSatisfied(Reservation reservation) {
        return isSatisfied(reservation.timePeriodOfUsage());
    }

    public boolean isSatisfied(@NonNull TimePeriodOfUsage timePeriodOfUsage) {
        // 30 分単位での予約のみ可能
        // 利用時間が 30 分で割り切れる。
        // 利用開始時間が 30 分で割り切れる。
        return true;
    }
}
