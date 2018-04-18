package com.epam.cdp.dao.impl;

import com.epam.cdp.dao.CrudRepository;
import com.epam.cdp.dao.mapping.EventMapper;
import com.epam.cdp.dao.sql.QueryContainer;
import com.epam.cdp.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Repository
public class EventDAO implements CrudRepository<Event, Long> {

    private Logger log = LoggerFactory.getLogger(EventDAO.class);

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Event save(Event event) {
        log.info("INSERT INTO public.event(id, title, date, price) VALUES " +
                "(" + event.getId() + ", " + event.getTitle() + ", " + event.getDate() + ", " + event.getPrice() + ");");
        jdbcTemplate.update(QueryContainer.INSERT_EVENT, event.getId(), event.getTitle(), event.getDate(), event.getPrice());
        return event;
    }

    @Override
    public Event update(Event event) {
        log.info("UPDATE public.event SET title=" + event.getTitle() + ", " +
                "date=" + event.getDate() + ", price=" + event.getPrice() + " WHERE id=" + event.getId() + ";");
        jdbcTemplate.update(QueryContainer.UPDATE_EVENT_BY_ID, event.getTitle(), event.getDate(), event.getPrice(), event.getId());
        return event;
    }

    @Override
    public Event findOne(Long id) {
        log.info("SELECT * FROM public.event WHERE id=" + id);
        return jdbcTemplate.queryForObject(QueryContainer.SELECT_EVENT_BY_ID, new Object[]{id}, new EventMapper());
    }

    @Override
    public void delete(Long id) {
        log.info("DELETE FROM public.event WHERE id=" + id);
        jdbcTemplate.update(QueryContainer.DELETE_EVENT_BY_ID, id);
    }

    @Override
    public Iterable<Event> findAll() {
        log.info(QueryContainer.SELECT_ALL_EVENTS);
        return jdbcTemplate.query(QueryContainer.SELECT_ALL_EVENTS, new EventMapper());
    }

    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        log.info(QueryContainer.SELECT_EVENTS_BY_TITLE);
        return jdbcTemplate.query(QueryContainer.SELECT_EVENTS_BY_TITLE,
                new Object[]{title, pageNum * pageSize, pageSize}, new EventMapper());
    }

    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        log.info(QueryContainer.SELECT_EVENTS_BY_DAY);
        return jdbcTemplate.query(QueryContainer.SELECT_EVENTS_BY_DAY,
                new Object[]{day, pageNum * pageSize, pageSize}, new EventMapper());
    }

}
