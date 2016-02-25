package com.ess.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import com.google.common.base.Optional;

public class SimpleAuthenticator implements
		Authenticator<BasicCredentials, Token> {

	public Optional<Token> authenticate(BasicCredentials credentials)
			throws AuthenticationException {
        if ("secret".equals(credentials.getPassword())) {
            return Optional.of(new Token(credentials.getUsername()));
        }
        return Optional.absent();
	}

}