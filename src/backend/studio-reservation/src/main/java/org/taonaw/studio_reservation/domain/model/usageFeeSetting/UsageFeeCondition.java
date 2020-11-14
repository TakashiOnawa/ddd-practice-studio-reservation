package org.taonaw.studio_reservation.domain.model.usageFeeSetting;

import lombok.NonNull;

import java.util.List;

public interface UsageFeeCondition {
    default boolean isTypeIn(@NonNull List<UsageFeeCondition> usageFeeConditions) {
        return usageFeeConditions.stream().anyMatch(condition -> this.getClass() == condition.getClass());
    }

    default boolean isTypeNotIn(@NonNull List<UsageFeeCondition> usageFeeConditions) {
        return !isTypeIn(usageFeeConditions);
    }

    default boolean isTypeDuplicated(@NonNull List<UsageFeeCondition> others) {
        return others.stream().anyMatch(this::isTypeDuplicated);
    }

    default boolean isTypeDuplicated(@NonNull UsageFeeCondition other) {
        return this != other && this.getClass() == other.getClass();
    }

    default boolean isDuplicated(@NonNull List<UsageFeeCondition> others) {
        return others.stream().anyMatch(this::isDuplicated);
    }

    default boolean isDuplicated(@NonNull UsageFeeCondition other) {
        return this != other && this.equals(other);
    }
}
