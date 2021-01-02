package org.taonaw.studio_reservation.domain.model.usageFeeSetting.basicUsageFeeSetting;

import de.huxhorn.sulky.ulid.ULID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class BasicUsageFeeSettingId {
    private final String value;

    public BasicUsageFeeSettingId(String value) {
        Assertion.required(value);
        this.value = value;
    }

    public static BasicUsageFeeSettingId newId() {
        return new BasicUsageFeeSettingId(new ULID().nextULID());
    }
}
