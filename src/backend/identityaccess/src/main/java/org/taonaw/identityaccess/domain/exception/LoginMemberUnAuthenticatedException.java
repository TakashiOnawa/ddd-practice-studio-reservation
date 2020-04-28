package org.taonaw.identityaccess.domain.exception;

import lombok.Getter;
import lombok.NonNull;
import org.taonaw.identityaccess.domain.model.shared.EmailAddress;

@Getter
public class LoginMemberUnAuthenticatedException extends DomainException {
    private final String emailAddress;
    private final String password;

    public LoginMemberUnAuthenticatedException(@NonNull EmailAddress emailAddress, @NonNull String password) {
        super("会員のログインパスワードが一致しません。");
        this.emailAddress = emailAddress.getValue();
        this.password = password;
    }
}
