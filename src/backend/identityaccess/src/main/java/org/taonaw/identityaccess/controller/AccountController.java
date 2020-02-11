package org.taonaw.identityaccess.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taonaw.identityaccess.application.AccountAppService;
import org.taonaw.identityaccess.query.IAccountQuery;
import org.taonaw.identityaccess.query.dto.AccountQueryDto;

@RestController
@RequestMapping(path = "/accounts")
@AllArgsConstructor
public class AccountController {

    @Autowired
    private final AccountAppService accountAppService;
    @Autowired
    private final IAccountQuery accountQuery;

    @GetMapping
    public ResponseEntity<AccountQueryDto> getAccount(@RequestParam("login_id") String loginId) {
        var account = accountQuery.accountByLoginId(loginId);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
