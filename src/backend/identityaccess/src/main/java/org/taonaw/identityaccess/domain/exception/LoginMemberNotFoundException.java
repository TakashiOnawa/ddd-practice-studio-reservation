package org.taonaw.identityaccess.domain.exception;

import lombok.Getter;
import lombok.NonNull;
import org.taonaw.identityaccess.domain.model.shared.EmailAddress;

@Getter
public class LoginMemberNotFoundException extends DomainException {
    private final String emailAddress;

    public LoginMemberNotFoundException(@NonNull EmailAddress emailAddress) {
        super("会員が存在しません。");
        this.emailAddress = emailAddress.getValue();
    }
}
