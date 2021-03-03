package com.woniuxy.component;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;
@Data
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {

        return this.token;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }
}
