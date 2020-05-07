package org.taonaw.identityaccess.api;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.taonaw.identityaccess.api.error.ErrorCode;
import org.taonaw.identityaccess.api.error.ErrorInformation;
import org.taonaw.identityaccess.api.error.ErrorResponse;
import org.taonaw.identityaccess.application.command.login_account.LoginAccountAppService;
import org.taonaw.identityaccess.application.command.login_account.LoginAccountCommand;
import org.taonaw.identityaccess.application.command.login_account.LoginAccountResult;
import org.taonaw.identityaccess.application.command.register_account.RegisterAccountAppService;
import org.taonaw.identityaccess.application.command.register_account.RegisterAccountCommand;
import org.taonaw.identityaccess.application.query.account.AccountDto;
import org.taonaw.identityaccess.application.query.account.IAccountQuery;
import org.taonaw.identityaccess.domain.exception.AccountDuplicatedException;
import org.taonaw.identityaccess.domain.exception.LoginAccountNotFoundException;
import org.taonaw.identityaccess.domain.exception.LoginAccountUnAuthenticatedException;

import java.util.List;

@RestController
@AllArgsConstructor
@ControllerAdvice
public class AccountController {
    @Autowired
    private final LoginAccountAppService loginAccountAppService;
    @Autowired
    private final RegisterAccountAppService registerAccountAppService;
    @Autowired
    private final IAccountQuery accountQuery;

    @PostMapping("/accounts/login")
    public ResponseEntity<LoginAccountResult> login(@RequestBody LoginAccountCommand request) {
        var response = loginAccountAppService.handle(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("accountId") String accountId) {
        var account = accountQuery.getByAccountId(accountId);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDto>> getAccounts() {
        var accounts = accountQuery.getAll();
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("/accounts")
    public ResponseEntity<Void> registerAccount(
            @RequestBody RegisterAccountCommand request,
            UriComponentsBuilder uriComponentsBuilder) {
        var response = registerAccountAppService.handle(request);
        var uri = uriComponentsBuilder.path("/accounts/{accountId}").buildAndExpand(response.getAccountId()).toUri();
        return ResponseEntity.created(uri).build();
    }



    @ExceptionHandler(LoginAccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(LoginAccountNotFoundException exception) {
        var error = ErrorInformation.builder()
                .code(ErrorCode.LoginAccountNotFound.getCode())
                .message("ログインアカウントが見つかりません。")
                .build();
        var response = new ErrorResponse(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(LoginAccountUnAuthenticatedException.class)
    public ResponseEntity<ErrorResponse> handleException(LoginAccountUnAuthenticatedException exception) {
        var error = ErrorInformation.builder()
                .code(ErrorCode.LoginAccountNotFound.getCode())
                .message("ログインパスワードが一致しません。")
                .build();
        var response = new ErrorResponse(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(AccountDuplicatedException.class)
    public ResponseEntity<ErrorResponse> handleException(AccountDuplicatedException exception) {
        var error = ErrorInformation.builder()
                .code(ErrorCode.AccountDuplicated.getCode())
                .message("アカウントが重複しています。")
                .build();
        var response = new ErrorResponse(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
