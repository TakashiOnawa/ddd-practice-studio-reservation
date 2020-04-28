package org.taonaw.identityaccess.domain.exception;

import lombok.Getter;
import lombok.NonNull;
import org.taonaw.identityaccess.domain.model.account.LoginId;

@Getter
public class LoginAccountNotFoundException extends DomainException {
    private final String loginId;

    public LoginAccountNotFoundException(@NonNull LoginId loginId) {
        super("ログインアカウントが存在しません。");
        this.loginId = loginId.getValue();
    }
}
