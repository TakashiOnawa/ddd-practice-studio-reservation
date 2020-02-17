package org.taonaw.identityaccess.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taonaw.identityaccess.application.AccountAppService;
import org.taonaw.identityaccess.query.IAccountQuery;
import org.taonaw.identityaccess.query.dto.AccountQueryDto;

import java.util.List;

@RestController
@AllArgsConstructor
public class AccountController {

    @Autowired
    private final AccountAppService accountAppService;
    @Autowired
    private final IAccountQuery accountQuery;

    @GetMapping(value = "/accounts", params = {"loginid"})
    public ResponseEntity<AccountQueryDto> getAccount(@RequestParam("loginid") String loginId) {
        var account = accountQuery.findByLoginId(loginId);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountQueryDto>> getAccounts() {
        var accounts = accountQuery.findAll();
        return ResponseEntity.ok(accounts);
    }
}
