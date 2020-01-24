package org.taonaw.identityaccess.application.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.identityaccess.domain.model.roles.RoleId;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class RegisterAccountRequest {
    @NonNull
    private String accountName;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String password;
    @NonNull
    private List<String> roles;

    public List<RoleId> getRoleIds() {
        return roles.stream().map(RoleId::new).collect(Collectors.toList());
    }
}
