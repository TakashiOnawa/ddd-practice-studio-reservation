package org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.error.UsageFeeConditionDuplicatedError;
import org.taonaw.studio_reservation.domain.shared.Assertion;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class UsageFeeConditions {
    private final List<UsageFeeCondition> items;

    public UsageFeeConditions(List<UsageFeeCondition> items) {
        Assertion.required(items);
        this.items = new ArrayList<>(items);
    }

    public List<UsageFeeCondition> items() {
        return new ArrayList<>(items);
    }

    public Optional<Error> validateConditionTypeDuplicated() {
        var errorItems = items.stream()
                .filter(item -> items.stream().anyMatch(item::isConditionTypeDuplicated))
                .collect(Collectors.toList());
        if (errorItems.isEmpty())
            return Optional.empty();
        else
            return Optional.of(new UsageFeeConditionDuplicatedError(errorItems));
    }

    public boolean isConditionTypeEqual(@NonNull UsageFeeConditionTypes usageFeeConditionTypes) {
        return items.size() == usageFeeConditionTypes.size() &&
                items.stream().allMatch(item -> usageFeeConditionTypes.contains(item.getConditionType()));
    }

    public boolean isDuplicated(@NonNull UsageFeeConditions other) {
        return items.size() == other.items.size() &&
                items.stream().allMatch(item -> other.items.stream().anyMatch(item::isDuplicated));
    }

    public UsageFeeConditions remove(@NonNull UsageFeeConditionTypes usageFeeConditionTypes) {
        var removedItems = items.stream()
                .filter(condition -> !usageFeeConditionTypes.contains(condition.getConditionType()))
                .collect(Collectors.toList());
        return new UsageFeeConditions(removedItems);
    }

    public <T extends UsageFeeCondition> Optional<T> first(@NonNull Class<T> type) {
        return items.stream()
                .filter(item -> item.getClass().equals(type))
                .map(type::cast)
                .findFirst();
    }
}
