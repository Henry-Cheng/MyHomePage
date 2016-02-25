package com.ess.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	@JsonProperty
	private String name;

	@JsonProperty
	private String email;

	private User() {
		// Jackson deserialization
	}

	public User(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User user = (User)obj;
			if (user.getEmail().equals(this.getEmail()) && 
					user.getName().equals(this.getName()))
			{
				return true;
			}
		}

		return super.equals(obj);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// hashCode
	// equals
	// toString etc.
}