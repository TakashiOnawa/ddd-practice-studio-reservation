package org.taonaw.authentication.domain.model.accounts;

import lombok.NonNull;
import org.taonaw.authentication.domain.model.roles.RoleId;

import java.util.Objects;

public class Account {
    private final AccountId accountId;
    private RoleId roleId;
    private FullName fullName;
    private DateOfBirth dateOfBirth;
    private EmailAddress emailAddress;
    private Password password;

    private Account(@NonNull AccountId accountId) {
        this.accountId = accountId;
    }

    public static Account newAccount(@NonNull RoleId roleId,
                                     @NonNull FullName fullName,
                                     @NonNull DateOfBirth dateOfBirth,
                                     @NonNull EmailAddress emailAddress,
                                     @NonNull Password password) {
        var account = new Account(new AccountId());
        account.roleId = roleId;
        account.fullName = fullName;
        account.dateOfBirth = dateOfBirth;
        account.emailAddress = emailAddress;
        account.password = password;
        return account;
    }

    public static Account reconstruct(@NonNull AccountId accountId,
                                      @NonNull RoleId roleId,
                                      @NonNull FullName fullName,
                                      @NonNull DateOfBirth dateOfBirth,
                                      @NonNull EmailAddress emailAddress,
                                      @NonNull Password password) {
        var account = new Account(accountId);
        account.roleId = roleId;
        account.fullName = fullName;
        account.dateOfBirth = dateOfBirth;
        account.emailAddress = emailAddress;
        account.password = password;
        return account;
    }

    public boolean isEmailAddressEquals(@NonNull Account other) {
        return this.emailAddress.equals(other.emailAddress);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId.equals(account.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }
}
