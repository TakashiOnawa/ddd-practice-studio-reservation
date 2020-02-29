package org.taonaw.identityaccess.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taonaw.identityaccess.application.temp.LoginAccountAppService;
import org.taonaw.identityaccess.application.registeraccount.RegisterAccountAppService;
import org.taonaw.identityaccess.application.temp.LoginAccountRequest;
import org.taonaw.identityaccess.application.temp.LoginAccountResponse;
import org.taonaw.identityaccess.application.registeraccount.RegisterAccountRequest;
import org.taonaw.identityaccess.domain.shared.exception.DomainException;
import org.taonaw.identityaccess.domain.shared.exception.DomainExceptionCodes;
import org.taonaw.identityaccess.query.account.GetAccountsResponse;
import org.taonaw.identityaccess.query.account.IAccountQuery;
import org.taonaw.identityaccess.query.account.AccountDto;

@RestController
@AllArgsConstructor
public class AccountController {

    @Autowired
    private final LoginAccountAppService loginAccountAppService;
    @Autowired
    private final RegisterAccountAppService registerAccountAppService;
    @Autowired
    private final IAccountQuery accountQuery;

    @PostMapping("/accounts/login")
    public ResponseEntity<LoginAccountResponse> login(@RequestBody LoginAccountRequest request) {
        try {
            var response = loginAccountAppService.handle(request);
            return ResponseEntity.ok(response);
        } catch (DomainException ex) {
            if (ex.in(DomainExceptionCodes.LoginAccountNotFound, DomainExceptionCodes.LoginAccountPasswordNotMatched)) {
                // TODO:レスポンスボディーに詳細なエラーを詰める。
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            throw ex;
        }
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("accountId") String accountId) {
        var account = accountQuery.getAccountById(accountId);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @GetMapping(value = "/accounts", params = {"loginid"})
//    public ResponseEntity<AccountQueryDto> getAccount(@RequestParam("loginid") String loginId) {
//        var account = accountQuery.findByLoginId(loginId);
//        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @GetMapping("/accounts")
    public ResponseEntity<GetAccountsResponse> getAccounts() {
        var accounts = accountQuery.getAccounts();
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("/accounts")
    public ResponseEntity<Void> registerAccount(@RequestBody RegisterAccountRequest request) {
        registerAccountAppService.handle(request);
        return ResponseEntity.ok().build();
    }
}
