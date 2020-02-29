package org.taonaw.managementsite.controller.account;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.taonaw.managementsite.application.identityaccess.IdentityAccessService;
import org.taonaw.managementsite.controller.account.form.AccountRegistrationForm;

@Controller
@AllArgsConstructor
public class AccountController {

    @Autowired
    private final IdentityAccessService identityAccessService;

    @GetMapping("/accounts")
    public String list(Model model) {
        var response = identityAccessService.getAccounts();
        model.addAttribute("accounts", response.getBody().getAccounts());
        return "account/list";
    }

    @GetMapping("/accounts/new")
    public String newAccount(Model model) {
        model.addAttribute("accountRegistrationForm", new AccountRegistrationForm());
        return "account/new";
    }

    @PostMapping("/accounts")
    public String newAccount(@ModelAttribute @Validated AccountRegistrationForm accountRegistrationForm,
                             BindingResult bindingResult,
                             Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("validationError", "不正な入力があります。");
            return "account/new";
        }

        // TODO:登録処理

        return "redirect:/accounts";
    }
}
