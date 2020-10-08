package org.taonaw.studio_reservation.domain.model.reservation;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.openingHourSetting.OpeningHour;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.ReservationStartDateTime;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.UserMaxCount;
import org.taonaw.studio_reservation.domain.model.reservation.error.*;
import org.taonaw.studio_reservation.domain.model.studio.EquipmentMaxUsableCount;
import org.taonaw.studio_reservation.domain.model.studio.StartTimes;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationRule {
    private final OpeningHour openingHour;
    private final ReservationStartDateTime reservationStartDateTime;
    private final UserMaxCount userMaxCount;
    private final StartTimes startTime;
    private final List<EquipmentMaxUsableCount> equipmentMaxUsableCounts;

    public ReservationRule(
            @NonNull OpeningHour openingHour,
            @NonNull ReservationStartDateTime reservationStartDateTime,
            @NonNull UserMaxCount userMaxCount,
            @NonNull StartTimes startTime,
            @NonNull List<EquipmentMaxUsableCount> equipmentMaxUsableCounts) {

        this.openingHour = openingHour;
        this.reservationStartDateTime = reservationStartDateTime;
        this.userMaxCount = userMaxCount;
        this.startTime = startTime;
        this.equipmentMaxUsableCounts = new ArrayList<>(equipmentMaxUsableCounts);
    }

    public Optional<Error> validateOpeningHour(@NonNull UsageTime usageTime) {
        if (!usageTime.satisfy(openingHour)) {
            return Optional.of(new OutOfOpeningHourError());
        }
        return Optional.empty();
    }

    public Optional<Error> validateReservationStartDateTime(@NonNull UsageTime usageTime) {
        if (!usageTime.satisfy(reservationStartDateTime)) {
            return Optional.of(new NotStartReservationError());
        }
        return Optional.empty();
    }

    public Optional<Error> validateUserMaxCount(@NonNull UserCount userCount) {
        if (!userCount.satisfy(userMaxCount)) {
            return Optional.of(new OverUserMaxCountError());
        }
        return Optional.empty();
    }

    public Optional<Error> validateStartTime(@NonNull UsageTime usageTime) {
        if (!usageTime.satisfy(startTime)) {
            return Optional.of(new StartTimeInvalidError());
        }
        return Optional.empty();
    }

    public Optional<Error> validateEquipmentMaxUsableCount(@NonNull UsageEquipments usageEquipments) {
        var errorEquipmentIds = usageEquipments.notSatisfyEquipments(equipmentMaxUsableCounts);
        if (!errorEquipmentIds.isEmpty()) {
            return Optional.of(new OverEquipmentMaxCountError(errorEquipmentIds));
        }
        return Optional.empty();
    }
}
