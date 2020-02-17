package org.taonaw.managementsite.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestOperations;

import java.util.List;
import java.util.Map;

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
    public String newAccount() {
        return "account/new";
    }

    @PostMapping("/accounts")
    public String newAccount(Map<String,String> account) throws Exception {
        return "redirect:/accounts";
    }
}
