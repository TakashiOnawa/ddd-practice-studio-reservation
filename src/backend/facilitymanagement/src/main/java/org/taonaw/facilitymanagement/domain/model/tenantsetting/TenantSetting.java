package org.taonaw.facilitymanagement.domain.model.tenantsetting;

import lombok.NonNull;

public class TenantSetting {
    private TenantName tenantName;
    private OpeningHours openingHours;
    private MaxNumberOfUsers personalPracticeMaxNumberOfUsers;
    private ReservationStartDateTimeSetting reservationStartDateTimeSetting;

    private TenantSetting() { }

    public static TenantSetting defaultSetting() {
        return reconstruct(
                new TenantName("デフォルトスタジオ"),
                OpeningHours.ALL_DAY,
                new MaxNumberOfUsers(2),
                new ReservationStartDateTimeSetting(
                        new ReservationStartDateTime(2, ReservationStartDateType.MONTHS_AGO, 0),
                        new ReservationStartDateTime(1, ReservationStartDateType.DAYS_AGO, 21)));
    }

    public static TenantSetting reconstruct(@NonNull TenantName tenantName,
                                            @NonNull OpeningHours openingHours,
                                            @NonNull MaxNumberOfUsers personalPracticeMaxNumberOfUsers,
                                            @NonNull ReservationStartDateTimeSetting reservationStartDateTimeSetting) {
        var tenant = new TenantSetting();
        tenant.tenantName = tenantName;
        tenant.openingHours = openingHours;
        tenant.personalPracticeMaxNumberOfUsers = personalPracticeMaxNumberOfUsers;
        tenant.reservationStartDateTimeSetting = reservationStartDateTimeSetting;
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

    public ReservationStartDateTimeSetting getReservationStartDateTimeSetting() {
        return reservationStartDateTimeSetting;
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
        this.reservationStartDateTimeSetting =
                this.reservationStartDateTimeSetting.change(practiceType, reservationStartDateTime);
    }
}
