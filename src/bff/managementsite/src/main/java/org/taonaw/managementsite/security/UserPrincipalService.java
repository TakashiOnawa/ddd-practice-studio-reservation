package org.taonaw.managementsite.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.io.IOException;
import java.util.HashMap;

@Service
@AllArgsConstructor
public class UserPrincipalService implements UserDetailsService {

    @Autowired
    @Qualifier("identityaccessRestOptions")
    private final RestOperations identityaccessRestOptions;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var uri = "/accounts?accountName={accountName}";
        var uriVars = new HashMap<String, String>();
        uriVars.put("accountName", username);

        var response = identityaccessRestOptions.getForEntity(uri, String.class, uriVars);

        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new UsernameNotFoundException(username);
        }

        try {
            var mapper = new ObjectMapper();
            var map = mapper.readValue(response.getBody(), new TypeReference<HashMap<String, String>>() {});

            return User.withUsername(map.get("accountName"))
                    .password(map.get("password"))
//                    .authorities("")
                    .build();
        } catch (IOException e) {
            throw new UsernameNotFoundException(username, e);
        }
    }
}
