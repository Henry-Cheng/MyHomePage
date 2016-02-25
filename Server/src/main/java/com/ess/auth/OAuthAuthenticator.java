package com.ess.auth;

import com.google.common.base.Optional;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

public class OAuthAuthenticator implements Authenticator<String, Token> {

	public Optional<Token> authenticate(String credentials)
			throws AuthenticationException {
        if ("secret".equals(credentials)) {
            return Optional.of(new Token(credentials));
        }
        return Optional.absent();
	}

}