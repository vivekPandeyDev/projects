package com.vanlife.manage.filter;

import org.springframework.security.core.AuthenticationException;

public class NoOpenPortAuthenticationException extends AuthenticationException {
    public NoOpenPortAuthenticationException(String msg) {
        super(msg);
    }
}
