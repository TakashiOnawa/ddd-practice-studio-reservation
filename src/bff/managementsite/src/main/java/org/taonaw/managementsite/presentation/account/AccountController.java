package org.taonaw.managementsite.presentation.account;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.taonaw.managementsite.application.error.ErrorCode;
import org.taonaw.managementsite.application.error.ErrorResponse;
import org.taonaw.managementsite.application.identityaccess.IdentityAccessService;
import org.taonaw.managementsite.application.identityaccess.command.register_account.RegisterAccountRequest;
import org.taonaw.managementsite.presentation.account.form.AccountRegistrationForm;

import java.util.Objects;

@Controller
@AllArgsConstructor
public class AccountController {

    @Autowired
    private final IdentityAccessService identityAccessService;

    @GetMapping("/accounts")
    public String list(Model model) {
        var response = identityAccessService.getAccounts();
        Objects.requireNonNull(response.getBody());
        model.addAttribute("accounts", response.getBody());
        return "account/list";
    }

    @GetMapping("/accounts/new")
    public String newAccount(Model model) {
        model.addAttribute("accountRegistrationForm", new AccountRegistrationForm());
        return "account/new";
    }

    @PostMapping("/accounts")
    public String newAccount(@ModelAttribute @Validated AccountRegistrationForm form,
                             BindingResult bindingResult,
                             Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("validationError", "不正な入力があります。");
            return "account/new";
        }

        var request = RegisterAccountRequest.builder()
                .loginId(form.getLoginId())
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .password(form.getPassword())
                .build();

        try {
            identityAccessService.registerAccount(request);
        } catch (HttpClientErrorException e) {
            var errorResponse = ErrorResponse.ofOrElseThrow(e);
            if (errorResponse.exists(ErrorCode.AccountDuplicated)) {
                model.addAttribute("validationError", "入力されたログインIDは既に存在します。");
                return "account/new";
            }
            throw e;
        }

        return "redirect:/accounts";
    }
}
