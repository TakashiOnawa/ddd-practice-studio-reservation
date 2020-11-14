package org.taonaw.studio_reservation.usecase.command.memberAccount.loginMemberAccount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.shared.EmailAddress;
import org.taonaw.studio_reservation.domain.model.shared.PlainTextPassword;

@Getter
@Builder
@AllArgsConstructor
public class LoginMemberAccountCommand {
    private EmailAddress emailAddress;
    private PlainTextPassword plainTextPassword;
}
