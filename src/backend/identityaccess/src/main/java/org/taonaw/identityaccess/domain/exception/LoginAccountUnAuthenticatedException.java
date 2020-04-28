package org.taonaw.identityaccess.domain.exception;

import lombok.Getter;
import lombok.NonNull;
import org.taonaw.identityaccess.domain.model.account.LoginId;

@Getter
public class LoginAccountUnAuthenticatedException extends DomainException {
    private final String loginId;
    private final String password;

    public LoginAccountUnAuthenticatedException(@NonNull LoginId loginId, @NonNull String password) {
        super("ログインパスワードが一致しません。");
        this.loginId = loginId.getValue();
        this.password = password;
    }
}
