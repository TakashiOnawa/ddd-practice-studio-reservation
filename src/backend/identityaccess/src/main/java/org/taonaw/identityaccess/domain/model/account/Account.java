package org.taonaw.identityaccess.domain.model.account;

import lombok.NonNull;

import java.util.Objects;

public class Account {
    private final AccountId accountId;
    private AccountName accountName;
    private LoginId loginId;
    private Password password;

    private Account(@NonNull AccountId accountId) {
        this.accountId = accountId;
    }

    public static Account newAccount(@NonNull AccountName accountName,
                                     @NonNull LoginId loginId,
                                     @NonNull Password password) {
        var account = new Account(new AccountId());
        account.accountName = accountName;
        account.loginId = loginId;
        account.password = password;
        return account;
    }

    public static Account reconstruct(@NonNull AccountId accountId,
                                      @NonNull AccountName accountName,
                                      @NonNull LoginId loginId,
                                      @NonNull Password password) {
        var account = new Account(accountId);
        account.accountName = accountName;
        account.loginId = loginId;
        account.password = password;
        return account;
    }

    public AccountId accountId() { return accountId; }
    public AccountName accountName() { return accountName; }
    public LoginId loginId() { return loginId; }
    public Password password() { return password; }

    public boolean authenticate(@NonNull LoginId loginId,
                                @NonNull PlainTextPassword plainTextPassword,
                                @NonNull IPasswordEncoder passwordEncoder) {
        return this.loginId.equals(loginId) && plainTextPassword.matches(this.password, passwordEncoder);
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
