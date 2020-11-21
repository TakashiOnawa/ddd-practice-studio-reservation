package org.taonaw.studio_reservation.domain.model.usageFeeSetting.packFeeSetting;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFee.UsageFees;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition.UsageFeeConditionTypes;
import org.taonaw.studio_reservation.domain.shared.exception.ErrorNotification;

import java.util.Objects;

public class PackFeeSetting {
    private final PackFeeSettingId id;
    private PackName name;
    private UsageFeeConditionTypes usageFeeConditionTypes = UsageFeeConditionTypes.empty();
    private UsageFees usageFees = UsageFees.empty();

    private PackFeeSetting(@NonNull PackFeeSettingId id) {
        this.id = id;
    }

    public static PackFeeSetting create(
            @NonNull PackName name,
            @NonNull UsageFeeConditionTypes usageFeeConditionTypes) {

        var errorNotification = new ErrorNotification();
        errorNotification.addError(usageFeeConditionTypes.validateDuplicated());
        errorNotification.throwIfHasErrors("パック料金設定に不備があります。");

        var instance = new PackFeeSetting(PackFeeSettingId.newId());
        instance.name = name;
        instance.usageFeeConditionTypes = usageFeeConditionTypes;
        return instance;
    }

    public static PackFeeSetting reconstruct(
            PackFeeSettingId id,
            PackName name,
            UsageFeeConditionTypes usageFeeConditionTypes,
            UsageFees usageFees) {

        var instance = new PackFeeSetting(id);
        instance.name = name;
        instance.usageFeeConditionTypes = usageFeeConditionTypes;
        instance.usageFees = usageFees;
        return instance;
    }

    public void changeUsageFeeConditionTypes(@NonNull UsageFeeConditionTypes usageFeeConditionTypes) {
        var errorNotification = new ErrorNotification();
        errorNotification.addError(usageFeeConditionTypes.validateDuplicated());
        errorNotification.throwIfHasErrors("利用料金条件を変更できません。");

        var decreasedUsageFeeConditionTypes = this.usageFeeConditionTypes.remove(usageFeeConditionTypes);
        this.usageFeeConditionTypes = usageFeeConditionTypes;
        this.usageFees = this.usageFees.removeUsageFeeCondition(decreasedUsageFeeConditionTypes);
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
