package org.taonaw.identityaccess.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taonaw.identityaccess.application.commands.AuthenticateAccountRequest;

@RestController
@RequestMapping(path = "hello")
public class AuthenticationController {

    @PostMapping(path = "")
    public void login(@RequestBody AuthenticateAccountRequest request) {

    }
}
