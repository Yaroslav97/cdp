package com.epam.cdp.dao.mapping;

import com.epam.cdp.model.impl.UserAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UseAccountMapper implements RowMapper<UserAccount> {

    @Override
    public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserAccount(
                rs.getLong("user_id"),
                rs.getDouble("score")
        );
    }

}
