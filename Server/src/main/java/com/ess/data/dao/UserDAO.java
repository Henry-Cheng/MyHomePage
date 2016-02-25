package com.ess.data.dao;

import com.ess.data.model.User;
import com.ess.data.mapper.UserMapper;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(UserMapper.class)
public interface UserDAO {

	@SqlUpdate("insert into users_user (id, username) values (:id, :username)")
	void insert(@Bind("id") int id, @Bind("name") String name);

	@SqlQuery("select username, email from users_user where id = :id")
	User findById(@Bind("id") long id);
}