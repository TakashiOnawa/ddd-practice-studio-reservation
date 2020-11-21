package org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.error.UsageFeeConditionTypeDuplicatedError;
import org.taonaw.studio_reservation.domain.shared.Assertion;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class UsageFeeConditionTypes {
    private final List<UsageFeeConditionType> items;

    public UsageFeeConditionTypes(List<UsageFeeConditionType> items) {
        Assertion.required(items);
        this.items = new ArrayList<>(items);
    }

    public static UsageFeeConditionTypes empty() {
        return new UsageFeeConditionTypes(new ArrayList<>());
    }

    public List<UsageFeeConditionType> items() {
        return new ArrayList<>(items);
    }

    public Optional<Error> validateDuplicated() {
        var errorItems = items.stream()
                .filter(item -> items.stream().filter(item::equals).count() > 1)
                .collect(Collectors.toList());
        if (errorItems.isEmpty())
            return Optional.empty();
        else
            return Optional.of(new UsageFeeConditionTypeDuplicatedError(errorItems));
    }

    public UsageFeeConditionTypes remove(@NonNull UsageFeeConditionTypes usageFeeConditionTypes) {
        var newItems = new ArrayList<>(this.items);
        newItems.removeAll(usageFeeConditionTypes.items);
        return new UsageFeeConditionTypes(newItems);
    }

    public boolean contains(@NonNull UsageFeeConditionType usageFeeConditionType) {
        return items.contains(usageFeeConditionType);
    }
}
