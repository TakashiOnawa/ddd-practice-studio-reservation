package org.taonaw.managementsite.controller.account;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestOperations;
import org.taonaw.managementsite.controller.account.form.AccountRegistrationForm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class AccountController {

    @Autowired
    @Qualifier("identityaccessRestOptions")
    private final RestOperations identityaccessRestOptions;

    @GetMapping("/accounts")
    public String list(Model model) throws Exception {

        var uri = "/accounts";
        var response = identityaccessRestOptions.getForEntity(uri, String.class);

        var mapper = new ObjectMapper();
        var map = mapper.readValue(response.getBody(), new TypeReference<List<Map<String, String>>>() {});
        model.addAttribute("accounts", map);

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
