package org.taonaw.studio_reservation.domain.model.staffAccount;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class LoginId {
    private final String value;

    public LoginId(String value) {
        this.value = value;
    }
}
