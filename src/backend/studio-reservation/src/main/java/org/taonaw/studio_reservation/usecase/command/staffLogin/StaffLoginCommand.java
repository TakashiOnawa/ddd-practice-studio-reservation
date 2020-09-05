package org.taonaw.studio_reservation.usecase.command.staffLogin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.shared.PlainTextPassword;
import org.taonaw.studio_reservation.domain.model.staffAccount.LoginId;

@Getter
@Builder
@AllArgsConstructor
public class StaffLoginCommand {
    private LoginId loginId;
    private PlainTextPassword plainTextPassword;
}
