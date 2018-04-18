package com.epam.cdp.dao.impl;

import com.epam.cdp.dao.CrudRepository;
import com.epam.cdp.dao.mapping.UserMapper;
import com.epam.cdp.dao.sql.QueryContainer;
import com.epam.cdp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDAO implements CrudRepository<User, Long> {

    private Logger log = LoggerFactory.getLogger(UserDAO.class);

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User save(User user) {
        log.info("INSERT INTO public.user(id, name, email) " +
                "VALUES(" + user.getId() + ", " + user.getName() + ", " + user.getEmail() + ");");
        jdbcTemplate.update(QueryContainer.INSERT_USER, user.getId(), user.getName(), user.getEmail());
        return user;
    }

    @Override
    public User update(User user) {
        log.info("UPDATE public.user SET name=" + user.getName() + ", email=" + user.getEmail() + " WHERE id=" + user.getId() + ";");
        jdbcTemplate.update(QueryContainer.UPDATE_USER_BY_ID, user.getName(), user.getEmail(), user.getId());
        return user;
    }

    @Override
    public User findOne(Long id) {
        log.info("SELECT * FROM public.user WHERE id=" + id);
        return jdbcTemplate.queryForObject(QueryContainer.SELECT_USER_BY_ID, new Object[]{id}, new UserMapper());
    }

    @Override
    public void delete(Long id) {
        log.info("DELETE FROM public.user WHERE id=" + id);
        jdbcTemplate.update(QueryContainer.DELETE_USER_BY_ID, id);
    }

    @Override
    public Iterable<User> findAll() {
        log.info(QueryContainer.SELECT_ALL_USERS);
        return jdbcTemplate.query(QueryContainer.SELECT_ALL_USERS, new UserMapper());
    }

    public User getUserByEmail(String email) {
        log.info(QueryContainer.SELECT_USER_BY_EMAIL);
        return jdbcTemplate.queryForObject(QueryContainer.SELECT_USER_BY_EMAIL,
                new Object[]{email}, new UserMapper());
    }

    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        log.info(QueryContainer.SELECT_USERS_BY_NAME);
        return jdbcTemplate.query(QueryContainer.SELECT_USERS_BY_NAME,
                new Object[]{name, pageNum * pageSize, pageSize}, new UserMapper());
    }

}
