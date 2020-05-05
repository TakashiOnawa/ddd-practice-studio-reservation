package org.taonaw.managementsite.presentation.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String index(){
        return "login/index";
    }

    @GetMapping(value = "/login", params = { "error" })
    public String index(Model model){
        model.addAttribute("validationError", "ログイン ID またはパスワードが正しくありません。");
        return "login/index";
    }
}
