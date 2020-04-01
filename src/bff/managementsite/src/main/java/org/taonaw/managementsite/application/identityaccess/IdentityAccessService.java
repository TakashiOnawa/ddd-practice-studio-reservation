package org.taonaw.managementsite.application.identityaccess;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.taonaw.managementsite.application.identityaccess.command.login_account.LoginAccountRequest;
import org.taonaw.managementsite.application.identityaccess.command.login_account.LoginAccountResponse;
import org.taonaw.managementsite.application.identityaccess.command.register_account.RegisterAccountRequest;
import org.taonaw.managementsite.application.identityaccess.query.AccountDto;

import java.util.List;

@Service
@AllArgsConstructor
public class IdentityAccessService {

    @Autowired
    @Qualifier("identityAccessRestOptions")
    private final RestOperations identityAccessRestOptions;

    public ResponseEntity<LoginAccountResponse> loginAccount(LoginAccountRequest request) {
        var uri = "/accounts/login";
        return identityAccessRestOptions.postForEntity(uri, request, LoginAccountResponse.class);
    }

    public ResponseEntity<Void> registerAccount(RegisterAccountRequest request) {
        var uri = "/accounts";
        return identityAccessRestOptions.postForEntity(uri, request, Void.class);
    }

    public ResponseEntity<List<AccountDto>> getAccounts() {
        var uri = "/accounts";
        return identityAccessRestOptions.exchange(uri, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<AccountDto>>() {});
    }
}
