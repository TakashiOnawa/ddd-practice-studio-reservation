package org.taonaw.authentication.domain.model.accounts;

import lombok.NonNull;
import org.taonaw.authentication.domain.model.roles.RoleId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Account {
    private final AccountId accountId;
    private final AccountName accountName;
    private final List<RoleId> roles = new ArrayList<>();
    private Password password;
    private FullName fullName;

    private Account(@NonNull AccountId accountId,
                    @NonNull AccountName accountName) {
        this.accountId = accountId;
        this.accountName = accountName;
    }

    public static Account newAccount(@NonNull AccountName accountName,
                                     @NonNull FullName fullName,
                                     @NonNull Password password) {
        var account = new Account(new AccountId(), accountName);
        account.fullName = fullName;
        account.password = password;
        return account;
    }

    public static Account reconstruct(@NonNull AccountId accountId,
                                      @NonNull AccountName accountName,
                                      @NonNull List<RoleId> roles,
                                      @NonNull Password password,
                                      @NonNull FullName fullName) {
        var account = new Account(accountId, accountName);
        account.roles.addAll(roles);
        account.fullName = fullName;
        account.password = password;
        return account;
    }

    public void assignRole(@NonNull List<RoleId> roleIds) {
        roleIds.stream().forEach(this::assignRole);
    }

    public void assignRole(@NonNull RoleId roleId) {
        if (this.roles.stream().anyMatch(item -> item.equals(roleId))) {
            return;
        }
        this.roles.add(roleId);
    }

    boolean isAccountNameEquals(@NonNull Account other) {
        return this.accountName.equals(other.accountName);
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
