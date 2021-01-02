package org.taonaw.studio_reservation.domain.model.usageFeeSetting.basicUsageFeeSetting;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.PracticeType;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.ApplicablePeriod;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFee.UsageFees;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition.UsageFeeConditionTypes;
import org.taonaw.studio_reservation.domain.shared.exception.ErrorNotification;

import java.util.Objects;

public class BasicUsageFeeSetting {
    private final BasicUsageFeeSettingId id;
    private PracticeType practiceType;
    private ApplicablePeriod applicablePeriod;
    private UsageFeeConditionTypes usageFeeConditionTypes = UsageFeeConditionTypes.empty();
    private UsageFees usageFees = UsageFees.empty();

    private BasicUsageFeeSetting(@NonNull BasicUsageFeeSettingId id) {
        this.id = id;
    }

    public static BasicUsageFeeSetting create(
            @NonNull PracticeType practiceType,
            @NonNull ApplicablePeriod applicablePeriod) {

        var instance = new BasicUsageFeeSetting(BasicUsageFeeSettingId.newId());
        instance.practiceType = practiceType;
        instance.applicablePeriod = applicablePeriod;
        return instance;
    }

    public static BasicUsageFeeSetting reconstruct(
            BasicUsageFeeSettingId id,
            PracticeType practiceType,
            ApplicablePeriod applicablePeriod,
            UsageFeeConditionTypes usageFeeConditionTypes,
            UsageFees usageFees) {

        var instance = new BasicUsageFeeSetting(id);
        instance.practiceType = practiceType;
        instance.applicablePeriod = applicablePeriod;
        instance.usageFeeConditionTypes = usageFeeConditionTypes;
        instance.usageFees = usageFees;
        return instance;
    }

    public void setUsageFeeConditionTypes(
            @NonNull UsageFeeConditionTypes usageFeeConditionTypes,
            @NonNull ErrorNotification errorNotification) {

        errorNotification.addError(usageFeeConditionTypes.validateDuplicated());
        if (errorNotification.noErrors()) {
            var decreasedUsageFeeConditionTypes = this.usageFeeConditionTypes.remove(usageFeeConditionTypes);
            this.usageFeeConditionTypes = usageFeeConditionTypes;
            this.usageFees = this.usageFees.removeUsageFeeCondition(decreasedUsageFeeConditionTypes);
        }
    }

    public void setUsageFees(@NonNull UsageFees usageFees, @NonNull ErrorNotification errorNotification) {
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
        BasicUsageFeeSetting that = (BasicUsageFeeSetting) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
