package com.epam.cdp.dao.impl;

import com.epam.cdp.dao.CrudRepository;
import com.epam.cdp.dao.mapping.TicketMapper;
import com.epam.cdp.dao.sql.QueryContainer;
import com.epam.cdp.model.Event;
import com.epam.cdp.model.Ticket;
import com.epam.cdp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TicketDAO implements CrudRepository<Ticket, Long> {

    private Logger log = LoggerFactory.getLogger(TicketDAO.class);

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Ticket save(Ticket ticket) {
        log.info("INSERT INTO public.ticket(id, event_id, user_id, category, place) VALUES" +
                "(" + ticket.getId() + ", " + ticket.getEventId() + ", " + ticket.getUserId() +
                ", " + ticket.getCategory() + ", " + ticket.getPlace() + ");");
        jdbcTemplate.update(QueryContainer.INSERT_TICKET, ticket.getId(),
                ticket.getEventId(), ticket.getUserId(), ticket.getCategory().toString(), ticket.getPlace());
        return ticket;
    }

    @Override
    public Ticket update(Ticket ticket) {
        log.info("UPDATE public.ticket SET event_id=" + ticket.getEventId() + ", user_id=" + ticket.getUserId() +
                ", category=" + ticket.getCategory() + ", place=" + ticket.getPlace() + " WHERE id=" + ticket.getId() + ";");
        jdbcTemplate.update(QueryContainer.UPDATE_TICKET_BY_ID, ticket.getEventId(),
                ticket.getUserId(), ticket.getCategory().toString(), ticket.getPlace(), ticket.getId());
        return ticket;
    }

    @Override
    public Ticket findOne(Long id) {
        log.info("SELECT * FROM public.ticket WHERE id=" + id);
        return jdbcTemplate.queryForObject(QueryContainer.SELECT_TICKET_BY_ID, new Object[]{id}, new TicketMapper());
    }

    @Override
    public void delete(Long id) {
        log.info("DELETE FROM public.ticket WHERE id=" + id);
        jdbcTemplate.update(QueryContainer.DELETE_TICKET_BY_ID, id);
    }

    @Override
    public Iterable<Ticket> findAll() {
        log.info(QueryContainer.SELECT_ALL_TICKETS);
        return jdbcTemplate.query(QueryContainer.SELECT_ALL_TICKETS, new TicketMapper());
    }

    public void saveAllTickets(List<Ticket> list) {
        log.info("Batch insert into ticket table");
        jdbcTemplate.batchUpdate(QueryContainer.INSERT_TICKET, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Ticket ticket = list.get(i);
                preparedStatement.setLong(1, ticket.getId());
                preparedStatement.setLong(2, ticket.getEventId());
                preparedStatement.setLong(3, ticket.getUserId());
                preparedStatement.setString(4, String.valueOf(ticket.getCategory()));
                preparedStatement.setInt(5, ticket.getPlace());

                log.info("INSERT INTO public.ticket(id, event_id, user_id, category, place) " +
                        "VALUES(" + ticket.getId() + ", " + ticket.getEventId() + ", " + ticket.getUserId() +
                        ", " + ticket.getCategory() + ", " + ticket.getPlace() + ");");
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
    }

    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        log.info(QueryContainer.SELECT_TICKETS_BY_USER);
        return jdbcTemplate.query(QueryContainer.SELECT_TICKETS_BY_USER,
                new Object[]{user.getId(), pageNum * pageSize, pageSize}, new TicketMapper());
    }

    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        log.info(QueryContainer.SELECT_TICKETS_BY_EVENT);
        return jdbcTemplate.query(QueryContainer.SELECT_TICKETS_BY_EVENT,
                new Object[]{event.getId(), pageNum * pageSize, pageSize}, new TicketMapper());
    }

}
