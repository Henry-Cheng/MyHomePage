package com.ess.rest;

import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ess.data.model.User;
import com.ess.data.dao.UserDAO;

import com.codahale.metrics.annotation.Timed;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	private final UserDAO userDAO;

	public UserResource(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@GET
	@Path("/{personId}")
	@Timed
	public User getUser(@PathParam("personId") LongParam userId) {
		return findSafely(userId.get());
	}

	private User findSafely(long userId) {
		final User user = userDAO.findById(userId);
		return user;
	}
}