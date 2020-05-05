package org.taonaw.managementsite.presentation.account.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class AccountRegistrationForm {
    @NotEmpty(message = "ログインIDを入力してください")
    @Size(max = 16, message = "ログインIDは 16 文字以内で入力してください")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "ログインIDは英数のみで入力してください")
    private String loginId;

    @NotEmpty(message = "名を入力してください")
    @Size(max = 50, message = "名は 50 文字以内で入力してください")
    private String firstName;

    @NotEmpty(message = "姓を入力してください")
    @Size(max = 50, message = "姓は 50 文字以内で入力してください")
    private String lastName;

    @NotNull
    @Size(min = 8, max = 20, message = "パスワードは 8 文字以上 20 文字以内で入力してください")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "パスワードは英数のみで入力してください")
    private String password;
}
