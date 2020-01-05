package org.taonaw.authentication.application.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
public class RegisterAccountRequest {
    @NonNull
    private String roleId;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private Date dateOfBirth;
    @NonNull
    private String emailAddress;
    @NonNull
    private String password;
}
