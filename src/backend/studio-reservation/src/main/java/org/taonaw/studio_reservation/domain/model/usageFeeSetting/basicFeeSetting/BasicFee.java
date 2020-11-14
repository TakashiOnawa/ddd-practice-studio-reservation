package org.taonaw.studio_reservation.domain.model.usageFeeSetting.basicFeeSetting;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.UsageFee;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.UsageFeeCondition;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.error.UsageFeeConditionTypeDuplicatedError;
import org.taonaw.studio_reservation.domain.shared.Assertion;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@EqualsAndHashCode
public class BasicFee {
    private final UsageFee usageFee;
    private final List<UsageFeeCondition> usageFeeConditions;

    public BasicFee(UsageFee usageFee, List<UsageFeeCondition> usageFeeConditions) {
        Assertion.required(usageFee);
        Assertion.required(usageFeeConditions);

        // 料金条件種別が重複してはならない。
        validateUsageFeeConditionTypeDuplicated(usageFeeConditions).ifPresent(Error::throwError);

        this.usageFee = usageFee;
        this.usageFeeConditions = usageFeeConditions;
    }

    private Optional<Error> validateUsageFeeConditionTypeDuplicated(List<UsageFeeCondition> usageFeeConditions) {
        var errorConditions = new HashSet<Class<? extends UsageFeeCondition>>();
        for (var condition : usageFeeConditions) {
            if (condition.isTypeDuplicated(usageFeeConditions)) {
                errorConditions.add(condition.getClass());
            }
        }

        if (errorConditions.isEmpty())
            return Optional.empty();
        else
            return Optional.of(new UsageFeeConditionTypeDuplicatedError(new ArrayList<>(errorConditions)));
    }

    public boolean isUsageFeeConditionTypesDifferent(@NonNull BasicFee other) {
        return this.usageFeeConditions.size() != other.usageFeeConditions.size() ||
                this.usageFeeConditions.stream()
                        .anyMatch(condition -> condition.isTypeNotIn(other.usageFeeConditions));
    }

    public boolean isUsageFeeConditionTypesDifferent(@NonNull List<BasicFee> basicFees) {
        return basicFees.stream().anyMatch(this::isUsageFeeConditionTypesDifferent);
    }

    public boolean isDuplicated(@NonNull BasicFee other) {
        return this != other &&
                this.usageFeeConditions.size() == other.usageFeeConditions.size() &&
                this.usageFeeConditions.stream()
                        .allMatch(condition -> condition.isDuplicated(other.usageFeeConditions));
    }

    public boolean isDuplicated(@NonNull List<BasicFee> basicFees) {
        return basicFees.stream().anyMatch(this::isDuplicated);
    }

    public boolean hasUsageFeeCondition(@NonNull Class<? extends UsageFeeCondition> type) {
        return usageFeeConditions.stream().anyMatch(item -> item.getClass() == type);
    }

    public <T extends UsageFeeCondition> Optional<T> getUsageFeeCondition(@NonNull Class<T> type) {
        return usageFeeConditions.stream()
                .filter(usageFeeCondition -> usageFeeCondition.getClass() == type)
                .map(type::cast)
                .findFirst();
    }

    public <T extends UsageFeeCondition> boolean anyMatchUsageFeeCondition(
            @NonNull Class<T> type,
            @NonNull Predicate<T> predicate) {

        var conditionOptional = getUsageFeeCondition(type);

        if (conditionOptional.isEmpty())
            return false;
        else
            return predicate.test(conditionOptional.get());
    }
}
