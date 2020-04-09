package org.taonaw.identityaccess.domain.model.account;

import lombok.NonNull;
import org.taonaw.identityaccess.domain.model.shared.FullName;
import org.taonaw.identityaccess.domain.model.shared.IPasswordEncoder;
import org.taonaw.identityaccess.domain.model.shared.Password;
import org.taonaw.identityaccess.domain.model.shared.PlainTextPassword;

import java.util.Objects;

public class Account {
    private final AccountId accountId;
    private FullName name;
    private LoginId loginId;
    private Password password;

    private Account(@NonNull AccountId accountId) {
        this.accountId = accountId;
    }

    public static Account newAccount(@NonNull FullName name,
                                     @NonNull LoginId loginId,
                                     @NonNull Password password) {
        var account = new Account(AccountId.newId());
        account.name = name;
        account.loginId = loginId;
        account.password = password;
        return account;
    }

    public static Account reconstruct(@NonNull AccountId accountId,
                                      @NonNull FullName name,
                                      @NonNull LoginId loginId,
                                      @NonNull Password password) {
        var account = new Account(accountId);
        account.name = name;
        account.loginId = loginId;
        account.password = password;
        return account;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public FullName getName() {
        return name;
    }

    public LoginId getLoginId() {
        return loginId;
    }

    public Password getPassword() {
        return password;
    }

    public boolean authenticate(@NonNull LoginId loginId,
                                @NonNull PlainTextPassword plainTextPassword,
                                @NonNull IPasswordEncoder passwordEncoder) {
        return this.loginId.equals(loginId) && password.matches(plainTextPassword, passwordEncoder);
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
