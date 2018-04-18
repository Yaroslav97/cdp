package com.epam.cdp.dao.mapping;

import com.epam.cdp.model.Event;
import com.epam.cdp.model.impl.EventImpl;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventMapper implements RowMapper<Event> {

    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new EventImpl(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getDate("date"),
                rs.getDouble("price")
        );
    }

}
