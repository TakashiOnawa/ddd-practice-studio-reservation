package org.taonaw.studio_reservation.usecase.command.member.registerMemberAccount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.shared.*;

@Getter
@Builder
@AllArgsConstructor
public class RegisterMemberAccountCommand {
    private PersonName name;
    private EmailAddress emailAddress;
    private PlainTextPassword plainTextPassword;
    private DateOfBirth dateOfBirth;
    private PhoneNumber phoneNumber;
}
