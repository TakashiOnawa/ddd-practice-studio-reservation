package org.taonaw.identityaccess.application.command.register_member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class RegisterMemberCommand {
    @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private String password;
    @NonNull private LocalDate dateOfBirth;
    @NonNull private String telephoneAreaCode;
    @NonNull private String telephoneLocalNumber;
    @NonNull private String telephoneSubscriberNumber;
    @NonNull private String emailAddress;
}
