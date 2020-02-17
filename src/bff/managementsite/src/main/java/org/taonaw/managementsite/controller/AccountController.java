package org.taonaw.managementsite.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestOperations;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
        var map = mapper.readValue(response.getBody(), new TypeReference<List<HashMap<String, String>>>() {});
        model.addAttribute("accounts", map);

        return "account/list";
    }
}
