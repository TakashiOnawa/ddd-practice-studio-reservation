package org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFee;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.apache.commons.lang3.NotImplementedException;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.error.UsageFeeConditionTypeDifferentError;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.error.UsageFeeDuplicatedError;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition.UsageFeeCondition;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition.UsageFeeConditionTypes;
import org.taonaw.studio_reservation.domain.shared.Assertion;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class UsageFees {
    private final List<UsageFee> items;

    public UsageFees(List<UsageFee> items) {
        Assertion.required(items);
        this.items = new ArrayList<>(items);
    }

    public static UsageFees empty() {
        return new UsageFees(new ArrayList<>());
    }

    public List<UsageFee> items() {
        return new ArrayList<>(items);
    }

    public Optional<Error> validateUsageFeeConditionTypesDifferent(@NonNull UsageFeeConditionTypes usageFeeConditionTypes) {
        var errorItems = items.stream()
                .filter(item -> !item.isUsageFeeConditionsTypeEqual(usageFeeConditionTypes))
                .collect(Collectors.toList());
        if (errorItems.isEmpty())
            return Optional.empty();
        else
            return Optional.of(new UsageFeeConditionTypeDifferentError(errorItems));
    }

    public Optional<Error> validateDuplicated() {
        var errorItems = items.stream()
                .filter(item -> items.stream().anyMatch(item::isDuplicated))
                .collect(Collectors.toList());
        if (errorItems.isEmpty())
            return Optional.empty();
        else
            return Optional.of(new UsageFeeDuplicatedError(errorItems));
    }

    public UsageFees removeUsageFeeCondition(@NonNull UsageFeeConditionTypes usageFeeConditionTypes) {
        var removedConditionTypesItems = items.stream()
                .map(item -> item.removeBasicUsageFeeCondition(usageFeeConditionTypes))
                .collect(Collectors.toList());
        return new UsageFees(removedConditionTypesItems);
    }

    public <T extends UsageFeeCondition> List<T> getUsageFeeConditions(@NonNull Class<T> type) {
        var usageFeeConditions = new ArrayList<T>();
        for (var item : items) {
            item.getUsageFeeCondition(type).ifPresent(usageFeeConditions::add);
        }
        return usageFeeConditions;
    }

    public <T extends UsageFeeCondition> List<T> getGroupingUsageFeeConditions(
            @NonNull Class<T> type,
            @NonNull Class<? extends UsageFeeCondition>... group) {

        throw new NotImplementedException("");
    }
}
