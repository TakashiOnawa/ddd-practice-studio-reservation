package org.taonaw.studio_reservation.domain.model.usageFeeSetting.basicUsageFeeSetting;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.error.BasicUsageFeeDuplicatedError;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.error.UsageFeeConditionTypeDifferentError;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition.UsageFeeConditionTypes;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class BasicUsageFees {
    private final List<BasicUsageFee> items;

    public BasicUsageFees(@NonNull List<BasicUsageFee> items) {
        this.items = new ArrayList<>(items);
    }

    public static BasicUsageFees empty() {
        return new BasicUsageFees(new ArrayList<>());
    }

    public List<BasicUsageFee> items() {
        return new ArrayList<>(items);
    }

    public Optional<Error> validateUsageFeeConditionTypesDifferent(@NonNull UsageFeeConditionTypes usageFeeConditionTypes) {
        var errorItems = items.stream()
                .filter(item -> !item.isUsageFeeConditionsTypeIn(usageFeeConditionTypes))
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
            return Optional.of(new BasicUsageFeeDuplicatedError(errorItems));
    }

    public BasicUsageFees removeBasicUsageFeeCondition(@NonNull UsageFeeConditionTypes usageFeeConditionTypes) {
        var removedConditionTypesItems = items.stream()
                .map(item -> item.removeBasicUsageFeeCondition(usageFeeConditionTypes))
                .collect(Collectors.toList());
        return new BasicUsageFees(removedConditionTypesItems);
    }
}
