package org.taonaw.identityaccess.application.register_member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class RegisterMemberCommand {
    @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private String password;
    @NonNull private String telephoneAreaCode;
    @NonNull private String telephoneLocalNumber;
    @NonNull private String telephoneSubscriberNumber;
    @NonNull private String emailAddress;
}
