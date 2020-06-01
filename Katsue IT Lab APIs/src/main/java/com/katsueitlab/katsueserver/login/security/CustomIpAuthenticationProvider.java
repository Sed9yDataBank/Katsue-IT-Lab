package com.katsueitlab.katsueserver.login.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CustomIpAuthenticationProvider implements AuthenticationProvider {

    Set<String> whitelist = new HashSet<String>();

    public CustomIpAuthenticationProvider() {
        whitelist.add("197.0.0.0");
        whitelist.add("197.31.255.255");
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        WebAuthenticationDetails details = (WebAuthenticationDetails) auth.getDetails();
        String userIp = details.getRemoteAddress();

        if(! whitelist.contains(userIp)) {
            throw new BadCredentialsException("Invalid IP Address");
        }
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true; // or false, doesn't make any difference
    }
}
