package org.taonaw.studio_reservation.usecase.command.staff.registerStaffAccount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.shared.PersonName;
import org.taonaw.studio_reservation.domain.model.shared.PlainTextPassword;
import org.taonaw.studio_reservation.domain.model.staffAccount.LoginId;

@Getter
@Builder
@AllArgsConstructor
public class RegisterStaffAccountCommand {
    private PersonName name;
    private LoginId loginId;
    private PlainTextPassword plainTextPassword;
}
