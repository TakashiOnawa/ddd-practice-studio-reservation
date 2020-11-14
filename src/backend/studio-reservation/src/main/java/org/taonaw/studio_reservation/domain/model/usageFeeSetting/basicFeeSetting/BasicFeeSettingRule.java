package org.taonaw.studio_reservation.domain.model.usageFeeSetting.basicFeeSetting;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.openingHourSetting.OpeningHour;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.PracticeTypes;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.UserMaxCount;
import org.taonaw.studio_reservation.domain.model.studio.StudioId;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.UsageFeeCondition;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.error.UsageFeeConditionTypesDifferentError;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeConditions.*;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.util.*;
import java.util.stream.Collectors;

public class BasicFeeSettingRule {
    private final List<StudioId> allStudioIds;
    private final Map<PracticeTypes, UserMaxCount> practiceTypeUserMaxCounts;
    private final OpeningHour openingHour;

    public Optional<Error> validateUsageFeeConditionTypes(@NonNull List<BasicFee> basicFees) {
        if (basicFees.stream().anyMatch(basicFee -> basicFee.isUsageFeeConditionTypesDifferent(basicFees)))
            return Optional.of(new UsageFeeConditionTypesDifferentError());
        else
            return Optional.empty();
    }

    public Optional<Error> validateBasicFeeDuplicated(@NonNull List<BasicFee> basicFees) {
        var errorBasicFees = new HashSet<BasicFee>();

        for (var basicFee : basicFees) {
            if (basicFee.isDuplicated(basicFees)) {
                errorBasicFees.add(basicFee);
            }
        }

        if (errorBasicFees.isEmpty())
            return Optional.empty();
        else
            return Optional.of(new Error());
    }

    public Optional<Error> validateStudioCondition(@NonNull List<BasicFee> basicFees) {
        if (!hasUsageFeeCondition(basicFees, StudioCondition.class))
            return Optional.empty();

        // 全てのスタジオが条件に含まれていなければならない。
        var hasAllStudioCondition = allStudioIds.stream()
                .allMatch(studioId -> basicFees.stream()
                        .anyMatch(basicFee -> basicFee.anyMatchUsageFeeCondition(
                                StudioCondition.class,
                                studioCondition -> studioCondition.getStudioId().equals(studioId))));

        if (hasAllStudioCondition)
            return Optional.empty();

        return Optional.of(new Error());
    }

    public Optional<Error> validateDayTypeCondition(@NonNull List<BasicFee> basicFees) {
        if (!hasUsageFeeCondition(basicFees, DayTypeCondition.class))
            return Optional.empty();

        // 全ての曜日が条件に含まれていなければならない。
        for (var dayTypePattern : DayTypes.ALL_DAY_TYPE_PATTERNS) {
            var hasAllDaysCondition = dayTypePattern.getDayTypes().stream()
                    .allMatch(dayType -> basicFees.stream()
                            .anyMatch(basicFee -> basicFee.anyMatchUsageFeeCondition(
                                    DayTypeCondition.class,
                                    dayTypeCondition -> dayTypeCondition.getDayType().equals(dayType))));

            if (hasAllDaysCondition)
                return Optional.empty();
        }

        return Optional.of(new Error());
    }

    public Optional<Error> validateUserCountCondition(
            @NonNull List<BasicFee> basicFees,
            @NonNull PracticeTypes practiceType) {

        if (!hasUsageFeeCondition(basicFees, UserCountCondition.class))
            return Optional.empty();

        var userMaxCount = practiceTypeUserMaxCounts.get(practiceType);

        // 最大利用人数を超えてはならない。
        var overUserMaxCount = basicFees.stream()
                .anyMatch(basicFee -> basicFee.anyMatchUsageFeeCondition(
                    UserCountCondition.class,
                    userCountCondition -> userCountCondition.satisfy(userMaxCount)));

        if (overUserMaxCount)
            return Optional.empty();

        return Optional.of(new Error());
    }

    public Optional<Error> validateTimePeriodCondition(@NonNull List<BasicFee> basicFees) {
        if (!hasUsageFeeCondition(basicFees, TimePeriodCondition.class))
            return Optional.empty();

        // 営業時間が全て埋まらなければならない。
        var timePeriodConditions = basicFees.stream()
                .map(basicFee -> basicFee.getUsageFeeCondition(TimePeriodCondition.class))
                .flatMap(Optional::stream)
                .sorted(Comparator.comparing(TimePeriodCondition::getStartDateTime))
                .collect(Collectors.toList());


    }

    private boolean hasUsageFeeCondition(List<BasicFee> basicFees, Class<? extends UsageFeeCondition> type) {
        return basicFees.stream().anyMatch(basicFee -> basicFee.hasUsageFeeCondition(type));
    }
}
