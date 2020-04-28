package org.taonaw.identityaccess.domain.exception;

import lombok.Getter;
import lombok.NonNull;
import org.taonaw.identityaccess.domain.model.shared.EmailAddress;

@Getter
public class MemberDuplicatedException extends DomainException {
    private final String emailAddress;

    public MemberDuplicatedException(@NonNull EmailAddress emailAddress) {
        super("会員が重複しています。");
        this.emailAddress = emailAddress.getValue();
    }
}
