package org.taonaw.managementsite.application.identityaccess;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.taonaw.managementsite.application.identityaccess.getaccounts.GetAccountsResponse;
import org.taonaw.managementsite.application.identityaccess.loginaccount.LoginAccountRequest;
import org.taonaw.managementsite.application.identityaccess.loginaccount.LoginAccountResponse;

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

    public ResponseEntity<GetAccountsResponse> getAccounts() {
        var uri = "/accounts";
        return identityAccessRestOptions.getForEntity(uri, GetAccountsResponse.class);
    }
}
