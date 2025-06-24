package org.scoula.security.account.domain;

import org.springframework.security.core.GrantedAuthority;

public class AuthVO implements GrantedAuthority {
    private String username;
    private String auth;

    @Override
    public String getAuthority() {
        return auth;
    }
}
