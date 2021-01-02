package org.taonaw.studio_reservation.domain.model.usageFeeSetting.packFeeSetting;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.ApplicablePeriod;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFee.UsageFees;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition.UsageFeeConditionTypes;
import org.taonaw.studio_reservation.domain.shared.exception.ErrorNotification;

import java.util.Objects;

public class PackFeeSetting {
    private final PackFeeSettingId id;
    private PackName name;
    private ApplicablePeriod applicablePeriod;
    private UsageFeeConditionTypes usageFeeConditionTypes = UsageFeeConditionTypes.empty();
    private UsageFees usageFees = UsageFees.empty();

    private PackFeeSetting(@NonNull PackFeeSettingId id) {
        this.id = id;
    }

    public static PackFeeSetting create(
            @NonNull PackName name,
            @NonNull ApplicablePeriod applicablePeriod) {

        var instance = new PackFeeSetting(PackFeeSettingId.newId());
        instance.name = name;
        instance.applicablePeriod = applicablePeriod;
        return instance;
    }

    public static PackFeeSetting reconstruct(
            PackFeeSettingId id,
            PackName name,
            ApplicablePeriod applicablePeriod,
            UsageFeeConditionTypes usageFeeConditionTypes,
            UsageFees usageFees) {

        var instance = new PackFeeSetting(id);
        instance.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackFeeSetting that = (PackFeeSetting) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
