package org.taonaw.facilitymanagement.domain.model.tenantsetting;

import lombok.NonNull;
import org.taonaw.facilitymanagement.domain.shared.exception.DomainException;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class TenantSetting {
    private TenantName tenantName;
    private OpeningHours openingHours;
    private MaxNumberOfUsers personalPracticeMaxNumberOfUsers;
    private Map<PracticeType, ReservationStartDateTime> reservationStartTime = new HashMap<>();
    private Set<CancellationFeeRate> cancellationFeeRates = new HashSet<>();

    private TenantSetting() { }

    public static TenantSetting defaultSetting() {
        return reconstruct(
                new TenantName("デフォルトスタジオ"),
                OpeningHours.ALL_DAY,
                new MaxNumberOfUsers(2),
                new ReservationStartDateTime(2, ReservationStartDateType.MONTHS_AGO, 0),
                new ReservationStartDateTime(1, ReservationStartDateType.DAYS_AGO, 21),
                new HashSet<>());
    }

    public static TenantSetting reconstruct(@NonNull TenantName tenantName,
                                            @NonNull OpeningHours openingHours,
                                            @NonNull MaxNumberOfUsers personalPracticeMaxNumberOfUsers,
                                            @NonNull ReservationStartDateTime bandReservationStartDateTime,
                                            @NonNull ReservationStartDateTime personalReservationStartDateTime,
                                            @NonNull Set<CancellationFeeRate> cancellationFeeRates) {
        var tenant = new TenantSetting();
        tenant.tenantName = tenantName;
        tenant.openingHours = openingHours;
        tenant.personalPracticeMaxNumberOfUsers = personalPracticeMaxNumberOfUsers;
        tenant.reservationStartTime.put(PracticeType.BAND, bandReservationStartDateTime);
        tenant.reservationStartTime.put(PracticeType.PERSONAL, personalReservationStartDateTime);
        tenant.cancellationFeeRates = cancellationFeeRates;
        return tenant;
    }

    public TenantName getTenantName() {
        return tenantName;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public MaxNumberOfUsers getPersonalPracticeMaxNumberOfUsers() {
        return personalPracticeMaxNumberOfUsers;
    }

    public MaxNumberOfUsers getMaxNumberOfUsers(@NonNull PracticeType practiceType) {
        return practiceType.equals(PracticeType.PERSONAL) ?
                personalPracticeMaxNumberOfUsers : MaxNumberOfUsers.UN_LIMITED;
    }

    public ReservationStartDateTime getReservationStartDateTime(@NonNull PracticeType practiceType) {
        return reservationStartTime.get(practiceType);
    }

    public Set<CancellationFeeRate> getCancellationFeeRates() {
        return Collections.unmodifiableSet(cancellationFeeRates);
    }

    public void changeTenantName(@NonNull TenantName tenantName) {
        this.tenantName = tenantName;
    }

    public void changeOpeningHours(@NonNull OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public void changePersonalPracticeMaxNumberOfUsers(@NonNull MaxNumberOfUsers maxNumberOfUsers) {
        this.personalPracticeMaxNumberOfUsers = maxNumberOfUsers;
    }

    public void changeReservationStartDateTime(@NonNull PracticeType practiceType,
                                               @NonNull ReservationStartDateTime reservationStartDateTime) {
        this.reservationStartTime.replace(practiceType, reservationStartDateTime);
    }

    public void changeCancellationFeeRates(@NonNull Set<CancellationFeeRate> cancellationFeeRates) {
        var sameDaysAgoExists = cancellationFeeRates.stream()
                .collect(groupingBy(CancellationFeeRate::getDaysAgo)).values().stream()
                .anyMatch(rates -> rates.size() > 1);
        if (sameDaysAgoExists) {
            throw new DomainException("同じ日付に対してキャンセル料金を設定することはできません。");
        }

        var sameRateExists = cancellationFeeRates.stream()
                .collect(groupingBy(CancellationFeeRate::getRate)).values().stream()
                .anyMatch(rates -> rates.size() > 1);
        if (sameRateExists) {
            throw new DomainException("複数の日付に同じキャンセル料金を設定することはできません。");
        }

        var sortedRates = cancellationFeeRates.stream()
                .sorted(Comparator.comparing(CancellationFeeRate::getDaysAgo))
                .collect(Collectors.toList());

        var currentRate = CancellationFeeRate.maxRate();
        for (var cancellationFeeRate : sortedRates) {
            if (currentRate < cancellationFeeRate.getRate()) {
                throw new DomainException("キャンセル料金は徐々に高くする必要があります。");
            }
            currentRate = cancellationFeeRate.getRate();
        }
    }
}
