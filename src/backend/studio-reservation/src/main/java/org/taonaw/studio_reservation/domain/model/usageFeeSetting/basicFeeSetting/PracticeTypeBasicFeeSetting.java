package org.taonaw.studio_reservation.domain.model.usageFeeSetting.basicFeeSetting;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.PracticeTypes;
import org.taonaw.studio_reservation.domain.shared.exception.ErrorNotification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PracticeTypeBasicFeeSetting {
    private final PracticeTypes practiceType;
    private final List<BasicFee> basicFees = new ArrayList<>();

    public PracticeTypeBasicFeeSetting(@NonNull PracticeTypes practiceType) {
        this.practiceType = practiceType;
    }

    public void setBasicFees(@NonNull List<BasicFee> basicFees, @NonNull BasicFeeSettingRule basicFeeSettingRule) {
        var errorNotification = new ErrorNotification();
        // 同じ料金条件種別を持たなければならない。
        errorNotification.addError(basicFeeSettingRule.validateUsageFeeConditionTypes(basicFees));
        // 料金条件の組み合わせが重複してはならない。
        errorNotification.addError(basicFeeSettingRule.validateBasicFeeDuplicated(basicFees));
        errorNotification.throwIfHasErrors("料金設定に不備があります。");

        this.basicFees.clear();
        this.basicFees.addAll(basicFees);
    }

    public PracticeTypes practiceType() {
        return practiceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PracticeTypeBasicFeeSetting that = (PracticeTypeBasicFeeSetting) o;
        return practiceType == that.practiceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(practiceType);
    }
}
