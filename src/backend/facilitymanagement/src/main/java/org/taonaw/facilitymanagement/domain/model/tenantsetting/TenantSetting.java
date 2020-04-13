package org.taonaw.facilitymanagement.domain.model.tenantsetting;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class TenantSetting {
    private TenantName tenantName;
    private OpeningHours openingHours;
    private MaxNumberOfUsers personalPracticeMaxNumberOfUsers;
    private Map<PracticeType, ReservationStartDateTime> reservationStartTime = new HashMap<>();

    private TenantSetting() { }

    public static TenantSetting defaultSetting() {
        return reconstruct(
                new TenantName("デフォルトスタジオ"),
                OpeningHours.ALL_DAY,
                new MaxNumberOfUsers(2),
                new ReservationStartDateTime(2, ReservationStartDateType.MONTHS_AGO, 0),
                new ReservationStartDateTime(1, ReservationStartDateType.DAYS_AGO, 21));
    }

    public static TenantSetting reconstruct(@NonNull TenantName tenantName,
                                            @NonNull OpeningHours openingHours,
                                            @NonNull MaxNumberOfUsers personalPracticeMaxNumberOfUsers,
                                            @NonNull ReservationStartDateTime bandReservationStartDateTime,
                                            @NonNull ReservationStartDateTime personalReservationStartDateTime) {
        var tenant = new TenantSetting();
        tenant.tenantName = tenantName;
        tenant.openingHours = openingHours;
        tenant.personalPracticeMaxNumberOfUsers = personalPracticeMaxNumberOfUsers;
        tenant.reservationStartTime.put(PracticeType.BAND, bandReservationStartDateTime);
        tenant.reservationStartTime.put(PracticeType.PERSONAL, personalReservationStartDateTime);
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
}
