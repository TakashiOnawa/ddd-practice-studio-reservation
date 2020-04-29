package org.taonaw.managementsite.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.taonaw.managementsite.application.identityaccess.IdentityAccessService;
import org.taonaw.managementsite.application.identityaccess.command.login_account.LoginAccountRequest;

@Component
@AllArgsConstructor
public class ExternalAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private final IdentityAccessService identityAccessService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException { }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {

        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }

        String password = authentication.getCredentials().toString();

        return login(username, password);
    }

    private UserDetails login(String username, String password) {
        var request = LoginAccountRequest.builder()
                .loginId(username)
                .password(password)
                .build();

        try {
            var response = identityAccessService.loginAccount(request);
            return User.withUsername(response.getBody().getLoginId())
                    .password("")
                    .authorities("GENERAL")
                    .build();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                throw new BadCredentialsException(messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.badCredentials",
                        "Bad credentials"));
            }
            throw e;
        }
    }
}
