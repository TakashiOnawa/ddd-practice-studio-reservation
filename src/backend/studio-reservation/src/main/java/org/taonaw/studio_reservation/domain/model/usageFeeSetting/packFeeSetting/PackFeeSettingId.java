package org.taonaw.studio_reservation.domain.model.usageFeeSetting.packFeeSetting;

import de.huxhorn.sulky.ulid.ULID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class PackFeeSettingId {
    private final String value;

    public PackFeeSettingId(String value) {
        Assertion.required(value);
        this.value = value;
    }

    public static PackFeeSettingId newId() {
        return new PackFeeSettingId(new ULID().nextULID());
    }
}
