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

    public PracticeTypeBasicUsageFeeSetting(@NonNull PracticeType practiceType) {
        this.practiceType = practiceType;
    }

    public void setUsageFeeConditionTypes(@NonNull UsageFeeConditionTypes usageFeeConditionTypes) {
        var errorNotification = new ErrorNotification();
        errorNotification.addError(usageFeeConditionTypes.validateDuplicated());
        errorNotification.throwIfHasErrors("利用料金条件を変更できません。");

        var decreasedUsageFeeConditionTypes = this.usageFeeConditionTypes.remove(usageFeeConditionTypes);
        this.usageFeeConditionTypes = usageFeeConditionTypes;
        this.usageFees = this.usageFees.removeUsageFeeCondition(decreasedUsageFeeConditionTypes);
    }

    public void setBasicFees(@NonNull UsageFees usageFees) {
        var errorNotification = new ErrorNotification();
        // 利用料金条件区分が、設定された値とい異なってはならない。
        errorNotification.addError(usageFees.validateUsageFeeConditionTypesDifferent(usageFeeConditionTypes));
        // 基本利用料金が重複してはならない。（料金条件の組み合わせが重複してはならない。）
        errorNotification.addError(usageFees.validateDuplicated());
        errorNotification.throwIfHasErrors("基本利用料金設定に不備があります。");

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
