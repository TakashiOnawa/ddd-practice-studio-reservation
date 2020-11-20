package org.taonaw.studio_reservation.domain.model.usageFeeSetting.packFeeSetting;

import lombok.NonNull;

import java.util.Objects;

public class PackFeeSetting {
    private final PackFeeSettingId id;
    private PackName packName;
    private PackFees packFees;

    private PackFeeSetting(@NonNull PackFeeSettingId id) {
        this.id = id;
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
