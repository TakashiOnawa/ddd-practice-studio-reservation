package org.taonaw.managementsite.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.taonaw.managementsite.application.error.ErrorCode;
import org.taonaw.managementsite.application.error.ErrorResponse;
import org.taonaw.managementsite.application.identityaccess.IdentityAccessService;
import org.taonaw.managementsite.application.identityaccess.command.login_account.LoginAccountRequest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

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

        var userForm = new UserForm(username, authentication.getCredentials().toString());
        var validationResults = validateUserForm(userForm);

        if (validationResults.size() > 0) {
            for (var validationResult : validationResults) {
                logger.debug(validationResult.getMessage());
            }

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad username of credentials"));
        }

        return login(userForm);
    }

    private Set<ConstraintViolation<UserForm>> validateUserForm(UserForm userForm) {
        var validator = Validation.buildDefaultValidatorFactory().getValidator();
        return validator.validate(userForm);
    }

    private UserDetails login(UserForm userForm) {
        var request = LoginAccountRequest.builder()
                .loginId(userForm.getLoginId())
                .password(userForm.getPassword())
                .build();

        try {
            var response = identityAccessService.loginAccount(request);
            var dto = Objects.requireNonNull(response.getBody());
            return User.withUsername(dto.getLoginId())
                    .password("")
                    .authorities("GENERAL")
                    .build();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                var errorResponse = ErrorResponse.of(e);

                if (errorResponse.exists(ErrorCode.LoginAccountNotFound)) {
                    throw new UsernameNotFoundException("user not found", e);
                }

                if (errorResponse.exists(ErrorCode.LoginAccountUnAuthenticated)) {
                    throw new BadCredentialsException(messages.getMessage(
                            "AbstractUserDetailsAuthenticationProvider.badCredentials",
                            "Bad credentials"), e);
                }
            }
            throw e;
        }
    }

    @Getter
    @AllArgsConstructor
    private static class UserForm {
        @NotEmpty(message = "ログインIDを入力してください")
        @Size(max = 16, message = "ログインIDは 16 文字以内で入力してください")
        @Pattern(regexp = "[a-zA-Z0-9]*", message = "ログインIDは英数のみで入力してください")
        private String loginId;

        @NotNull
        @Size(min = 8, max = 20, message = "パスワードは 8 文字以上 20 文字以内で入力してください")
        @Pattern(regexp = "[a-zA-Z0-9]*", message = "パスワードは英数のみで入力してください")
        private String password;
    }
}
