package org.taonaw.studio_reservation.domain.model.usageFeeSetting.basicUsageFeeSetting;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.PracticeType;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFee.UsageFees;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition.UsageFeeConditionTypes;
import org.taonaw.studio_reservation.domain.shared.exception.ErrorNotification;

import java.util.Objects;

public class PracticeTypeBasicUsageFeeSetting {
    private final PracticeType practiceType;
    private UsageFeeConditionTypes usageFeeConditionTypes = UsageFeeConditionTypes.empty();
    private UsageFees usageFees = UsageFees.empty();

    PracticeTypeBasicUsageFeeSetting(@NonNull PracticeType practiceType) {
        this.practiceType = practiceType;
    }

    public static PracticeTypeBasicUsageFeeSetting reconstruct(
            PracticeType practiceType,
            UsageFeeConditionTypes usageFeeConditionTypes,
            UsageFees usageFees) {

        var instance = new PracticeTypeBasicUsageFeeSetting(practiceType);
        instance.usageFeeConditionTypes = usageFeeConditionTypes;
        instance.usageFees = usageFees;
        return instance;
    }

    void setUsageFeeConditionTypes(
            @NonNull UsageFeeConditionTypes usageFeeConditionTypes,
            @NonNull ErrorNotification errorNotification) {

        errorNotification.addError(usageFeeConditionTypes.validateDuplicated());
        if (errorNotification.noErrors()) {
            var decreasedUsageFeeConditionTypes = this.usageFeeConditionTypes.remove(usageFeeConditionTypes);
            this.usageFeeConditionTypes = usageFeeConditionTypes;
            this.usageFees = this.usageFees.removeUsageFeeCondition(decreasedUsageFeeConditionTypes);
        }
    }

    void setUsageFees(@NonNull UsageFees usageFees, @NonNull ErrorNotification errorNotification) {
        errorNotification.addError(usageFees.validateUsageFeeConditionTypesDifferent(usageFeeConditionTypes));
        errorNotification.addError(usageFees.validateDuplicated());
        if (errorNotification.noErrors())
            this.usageFees = usageFees;
    }

    public PracticeType practiceType() {
        return practiceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PracticeTypeBasicUsageFeeSetting that = (PracticeTypeBasicUsageFeeSetting) o;
        return practiceType == that.practiceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(practiceType);
    }
}
