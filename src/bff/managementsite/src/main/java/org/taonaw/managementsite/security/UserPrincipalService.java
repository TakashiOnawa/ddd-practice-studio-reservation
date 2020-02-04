package org.taonaw.managementsite.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class UserPrincipalService implements UserDetailsService {

    @Autowired
    @Qualifier("reservationRestOptions")
    private final RestOperations reservationRestOptions;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var uri = "/accounts?accountName=" + username;
        if (false) {
            throw new UsernameNotFoundException(username);
        }

        return null;
    }
}
