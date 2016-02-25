package com.ess.auth;

public class Token {
	private final String name;

    public Token(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}