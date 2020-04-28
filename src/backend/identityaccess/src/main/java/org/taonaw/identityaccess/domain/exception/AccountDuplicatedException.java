package org.taonaw.identityaccess.domain.exception;

import lombok.Getter;
import lombok.NonNull;
import org.taonaw.identityaccess.domain.model.account.LoginId;

@Getter
public class AccountDuplicatedException extends DomainException {
    private final String loginId;

    public AccountDuplicatedException(@NonNull LoginId loginId) {
        super(String.format("アカウントが重複しています。loginId=%s", loginId.getValue()));
        this.loginId = loginId.getValue();
    }
}
