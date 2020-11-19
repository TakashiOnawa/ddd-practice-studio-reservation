package org.taonaw.studio_reservation.domain.model.reservation;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.equipment.EquipmentId;
import org.taonaw.studio_reservation.domain.model.equipment.EquipmentStockCount;
import org.taonaw.studio_reservation.domain.model.openingHourSetting.OpeningHour;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.ReservationStartDate;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.UserMaxCount;
import org.taonaw.studio_reservation.domain.model.reservation.error.*;
import org.taonaw.studio_reservation.domain.model.studio.EquipmentMaxUsableCount;
import org.taonaw.studio_reservation.domain.model.studio.StartTime;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ReservationRule {
    private final OpeningHour openingHour;
    private final ReservationStartDate reservationStartDate;
    private final UserMaxCount userMaxCount;
    private final StartTime startTime;
    private final Map<EquipmentId, EquipmentMaxUsableCount> equipmentMaxUsableCounts;
    private final Map<EquipmentId, EquipmentStockCount> equipmentStockCounts;

    public ReservationRule(
            @NonNull OpeningHour openingHour,
            @NonNull ReservationStartDate reservationStartDate,
            @NonNull UserMaxCount userMaxCount,
            @NonNull StartTime startTime,
            @NonNull Map<EquipmentId, EquipmentMaxUsableCount> equipmentMaxUsableCounts,
            @NonNull Map<EquipmentId, EquipmentStockCount> equipmentStockCounts) {

        this.openingHour = openingHour;
        this.reservationStartDate = reservationStartDate;
        this.userMaxCount = userMaxCount;
        this.startTime = startTime;
        this.equipmentMaxUsableCounts = new HashMap<>(equipmentMaxUsableCounts);
        this.equipmentStockCounts = new HashMap<>(equipmentStockCounts);
    }

    public Optional<Error> validateOpeningHour(@NonNull UsageTime usageTime) {
        if (usageTime.satisfy(openingHour))
            return Optional.empty();
        else
            return Optional.of(new OutOfOpeningHourError());
    }

    public Optional<Error> validateReservationStartDateTime(@NonNull UsageTime usageTime, LocalDateTime currentDateTime) {
        if (usageTime.satisfy(reservationStartDate, currentDateTime))
            return Optional.empty();
        else
            return Optional.of(new NotStartReservationError());
    }

    public Optional<Error> validateUserMaxCount(@NonNull UserCount userCount) {
        if (userCount.satisfy(userMaxCount))
            return Optional.empty();
        else
            return Optional.of(new OverUserMaxCountError());
    }

    public Optional<Error> validateStartTime(@NonNull UsageTime usageTime) {
        if (usageTime.satisfy(startTime))
            return Optional.empty();
        else
            return Optional.of(new StartTimeInvalidError());
    }

    public Optional<Error> validateEquipmentMaxUsableCount(@NonNull UsageEquipments usageEquipments) {
        var errorEquipmentIds = usageEquipments.notSatisfyEquipments(equipmentMaxUsableCounts);
        if (errorEquipmentIds.isEmpty())
            return Optional.empty();
        else
            return Optional.of(new OverEquipmentMaxCountError(errorEquipmentIds));
    }

    public Optional<Error> validateReservationDuplication(
            @NonNull Reservation reservation,
            @NonNull List<Reservation> overlappedReservations) {

        if (overlappedReservations.stream().anyMatch(item -> item.isDuplicated(reservation))) {
            return Optional.of(new ReservationDuplicatedError());
        }
        return Optional.empty();
    }

    public Optional<Error> validateUsageEquipmentsOutOfStocks(@NonNull ReservedUsageEquipments reservedUsageEquipments) {
        var errorEquipmentIds = reservedUsageEquipments.notSatisfyEquipments(equipmentStockCounts);
        if (errorEquipmentIds.isEmpty())
            return Optional.empty();
        else
            return Optional.of(new EquipmentOutOfStocksError(errorEquipmentIds));
    }
}
