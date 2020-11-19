package org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.error.UsageFeeConditionDuplicatedError;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class UsageFeeConditions {
    private final List<UsageFeeCondition> items;

    public UsageFeeConditions(@NonNull List<UsageFeeCondition> items) {
        this.items = new ArrayList<>(items);
    }

    public List<UsageFeeCondition> items() {
        return new ArrayList<>(items);
    }

    public Optional<Error> validateTypeDuplicated() {
        var errorItems = items.stream()
                .filter(item -> items.stream().anyMatch(item::isTypeDuplicated))
                .collect(Collectors.toList());
        if (errorItems.isEmpty())
            return Optional.empty();
        else
            return Optional.of(new UsageFeeConditionDuplicatedError(errorItems));
    }

    public boolean isTypeIn(@NonNull UsageFeeConditionTypes usageFeeConditionTypes) {
        return items.stream().allMatch(item -> item.isTypeIn(usageFeeConditionTypes));
    }

    public boolean isDuplicated(@NonNull UsageFeeConditions other) {
        return items.size() == other.items.size() &&
                items.stream().allMatch(item -> other.items.stream().anyMatch(item::isDuplicated));
    }

    public UsageFeeConditions remove(@NonNull UsageFeeConditionTypes usageFeeConditionTypes) {
        var removedItems = items.stream()
                .filter(condition -> !usageFeeConditionTypes.contains(condition.getType()))
                .collect(Collectors.toList());
        return new UsageFeeConditions(removedItems);
    }
}
