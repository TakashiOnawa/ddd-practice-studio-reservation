package org.taonaw.studio_reservation.usecase.command.staffAccount.loginStaffAccount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.shared.PlainTextPassword;
import org.taonaw.studio_reservation.domain.model.staffAccount.LoginId;

@Getter
@Builder
@AllArgsConstructor
public class LoginStaffAccountCommand {
    private LoginId loginId;
    private PlainTextPassword plainTextPassword;
}
