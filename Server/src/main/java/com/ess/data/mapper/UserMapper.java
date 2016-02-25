package com.ess.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.ess.data.model.User;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class UserMapper implements ResultSetMapper<User>
{
  public User map(int index, ResultSet r, StatementContext ctx) throws SQLException
  {
    return new User(r.getString("username"), r.getString("email"));
  }
}
