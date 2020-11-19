package org.taonaw.studio_reservation.domain.model.usageFeeSetting.basicUsageFeeSetting;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.openingHourSetting.OpeningHour;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.PracticeType;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.UserMaxCount;
import org.taonaw.studio_reservation.domain.model.studio.StudioId;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition.UsageFeeCondition;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition.*;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.util.*;
import java.util.stream.Collectors;

public class BasicUsageFeeSettingRule {
    private final List<StudioId> allStudioIds;
    private final Map<PracticeType, UserMaxCount> practiceTypeUserMaxCounts;
    private final OpeningHour openingHour;

    public BasicUsageFeeSettingRule(
            List<StudioId> allStudioIds,
            Map<PracticeType, UserMaxCount> practiceTypeUserMaxCounts,
            OpeningHour openingHour) {

        this.allStudioIds = allStudioIds;
        this.practiceTypeUserMaxCounts = practiceTypeUserMaxCounts;
        this.openingHour = openingHour;
    }

//    public Optional<Error> validateStudioCondition(@NonNull List<BasicUsageFee> basicUsageFees) {
//        if (!hasUsageFeeCondition(basicUsageFees, StudioCondition.class))
//            return Optional.empty();
//
//        // 全てのスタジオが条件に含まれていなければならない。
//        var hasAllStudioCondition = allStudioIds.stream()
//                .allMatch(studioId -> basicUsageFees.stream()
//                        .anyMatch(basicFee -> basicFee.anyMatchUsageFeeCondition(
//                                StudioCondition.class,
//                                studioCondition -> studioCondition.getStudioId().equals(studioId))));
//
//        if (hasAllStudioCondition)
//            return Optional.empty();
//
////            return Optional.of(new Error());
//        return Optional.empty();
//    }
//
//    public Optional<Error> validateDayTypeCondition(@NonNull List<BasicUsageFee> basicUsageFees) {
//        if (!hasUsageFeeCondition(basicUsageFees, DayTypeCondition.class))
//            return Optional.empty();
//
//        // 全ての曜日が条件に含まれていなければならない。
//        for (var dayTypePattern : DayType.ALL_DAY_TYPE_PATTERNS) {
//            var hasAllDaysCondition = dayTypePattern.getDayTypes().stream()
//                    .allMatch(dayType -> basicUsageFees.stream()
//                            .anyMatch(basicFee -> basicFee.anyMatchUsageFeeCondition(
//                                    DayTypeCondition.class,
//                                    dayTypeCondition -> dayTypeCondition.getDayType().equals(dayType))));
//
//            if (hasAllDaysCondition)
//                return Optional.empty();
//        }
//
////            return Optional.of(new Error());
//        return Optional.empty();
//    }
//
//    public Optional<Error> validateUserCountCondition(
//            @NonNull List<BasicUsageFee> basicUsageFees,
//            @NonNull PracticeType practiceType) {
//
//        if (!hasUsageFeeCondition(basicUsageFees, UserCountCondition.class))
//            return Optional.empty();
//
//        var userMaxCount = practiceTypeUserMaxCounts.get(practiceType);
//
//        // 最大利用人数を超えてはならない。
//        var overUserMaxCount = basicUsageFees.stream()
//                .anyMatch(basicFee -> basicFee.anyMatchUsageFeeCondition(
//                    UserCountCondition.class,
//                    userCountCondition -> userCountCondition.satisfy(userMaxCount)));
//
//        if (overUserMaxCount)
//            return Optional.empty();
//
////            return Optional.of(new Error());
//        return Optional.empty();
//    }
//
//    public Optional<Error> validateTimePeriodCondition(@NonNull List<BasicUsageFee> basicUsageFees) {
//        if (!hasUsageFeeCondition(basicUsageFees, TimePeriodCondition.class))
//            return Optional.empty();
//
//        // 営業時間が全て埋まらなければならない。
//        var timePeriodConditions = basicUsageFees.stream()
//                .map(basicFee -> basicFee.getUsageFeeCondition(TimePeriodCondition.class))
//                .flatMap(Optional::stream)
//                .sorted(Comparator.comparing(TimePeriodCondition::getStartDateTime))
//                .collect(Collectors.toList());
//
////            return Optional.of(new Error());
//        return Optional.empty();
//    }
//
//    private boolean hasUsageFeeCondition(List<BasicUsageFee> basicUsageFees, Class<? extends UsageFeeCondition> type) {
//        return basicUsageFees.stream().anyMatch(basicFee -> basicFee.hasUsageFeeCondition(type));
//    }
}
