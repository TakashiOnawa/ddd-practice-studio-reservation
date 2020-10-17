package org.taonaw.studio_reservation.domain.model.reservation;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.equipment.EquipmentId;
import org.taonaw.studio_reservation.domain.model.equipment.EquipmentStockCount;
import org.taonaw.studio_reservation.domain.model.openingHourSetting.OpeningHour;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.ReservationStartDateTime;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.UserMaxCount;
import org.taonaw.studio_reservation.domain.model.reservation.error.*;
import org.taonaw.studio_reservation.domain.model.studio.EquipmentMaxUsableCount;
import org.taonaw.studio_reservation.domain.model.studio.StartTimes;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ReservationRule {
    private final OpeningHour openingHour;
    private final ReservationStartDateTime reservationStartDateTime;
    private final UserMaxCount userMaxCount;
    private final StartTimes startTime;
    private final Map<EquipmentId, EquipmentMaxUsableCount> equipmentMaxUsableCounts;
    private final Map<EquipmentId, EquipmentStockCount> equipmentStockCounts;

    public ReservationRule(
            @NonNull OpeningHour openingHour,
            @NonNull ReservationStartDateTime reservationStartDateTime,
            @NonNull UserMaxCount userMaxCount,
            @NonNull StartTimes startTime,
            @NonNull Map<EquipmentId, EquipmentMaxUsableCount> equipmentMaxUsableCounts,
            @NonNull Map<EquipmentId, EquipmentStockCount> equipmentStockCounts) {

        this.openingHour = openingHour;
        this.reservationStartDateTime = reservationStartDateTime;
        this.userMaxCount = userMaxCount;
        this.startTime = startTime;
        this.equipmentMaxUsableCounts = new HashMap<>(equipmentMaxUsableCounts);
        this.equipmentStockCounts = new HashMap<>(equipmentStockCounts);
    }

    public Optional<Error> validateOpeningHour(@NonNull UsageTime usageTime) {
        if (!usageTime.satisfy(openingHour))
            return Optional.of(new OutOfOpeningHourError());
        else
            return Optional.empty();
    }

    public Optional<Error> validateReservationStartDateTime(@NonNull UsageTime usageTime) {
        if (!usageTime.satisfy(reservationStartDateTime))
            return Optional.of(new NotStartReservationError());
        else
            return Optional.empty();
    }

    public Optional<Error> validateUserMaxCount(@NonNull UserCount userCount) {
        if (!userCount.satisfy(userMaxCount))
            return Optional.of(new OverUserMaxCountError());
        else
            return Optional.empty();
    }

    public Optional<Error> validateStartTime(@NonNull UsageTime usageTime) {
        if (!usageTime.satisfy(startTime))
            return Optional.of(new StartTimeInvalidError());
        else
            return Optional.empty();
    }

    public Optional<Error> validateEquipmentMaxUsableCount(@NonNull UsageEquipments usageEquipments) {
        var errorEquipmentIds = usageEquipments.notSatisfyEquipments(equipmentMaxUsableCounts);
        if (!errorEquipmentIds.isEmpty())
            return Optional.of(new OverEquipmentMaxCountError(errorEquipmentIds));
        else
            return Optional.empty();
    }

    public Optional<Error> validateReservationDuplication(
            @NonNull Reservation reservation,
            @NonNull List<Reservation> overlappedReservations) {

        if (overlappedReservations.stream().anyMatch(item -> item.isDuplicated(reservation))) {
            return Optional.of(new ReservationDuplicatedError());
        }
        return Optional.empty();
    }

    public Optional<Error> validateUsageEquipmentsOutOfStocks(
            @NonNull Reservation reservation,
            @NonNull List<Reservation> overlappedReservations) {

        return Optional.empty();
    }
}
