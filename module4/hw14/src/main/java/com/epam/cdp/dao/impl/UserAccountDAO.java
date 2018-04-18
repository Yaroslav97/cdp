package com.epam.cdp.dao.impl;

import com.epam.cdp.dao.CrudRepository;
import com.epam.cdp.dao.mapping.UseAccountMapper;
import com.epam.cdp.dao.sql.QueryContainer;
import com.epam.cdp.model.impl.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserAccountDAO implements CrudRepository<UserAccount, Long> {

    private Logger log = LoggerFactory.getLogger(UserAccountDAO.class);

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public <S extends UserAccount> S save(S userAccount) {
        log.info("INSERT INTO public.user_account(user_id, score) VALUES (" + userAccount.getId() + ", " + userAccount.getScore() + ");");
        jdbcTemplate.update(QueryContainer.INSERT_USER_ACCOUNT, userAccount.getId(), userAccount.getScore());
        return userAccount;
    }

    @Override
    public <S extends UserAccount> S update(S userAccount) {
        log.info("UPDATE public.user_account SET score=" + userAccount.getScore() + " WHERE user_id=" + userAccount.getId() + ";");
        jdbcTemplate.update(QueryContainer.UPDATE_USER_ACCOUNT_BY_ID, userAccount.getScore(), userAccount.getId());
        return userAccount;
    }

    @Override
    public UserAccount findOne(Long id) {
        log.info("SELECT * FROM public.user_account WHERE user_id=" + id);
        return jdbcTemplate.queryForObject(QueryContainer.SELECT_USER_ACCOUNT_BY_ID, new Object[]{id}, new UseAccountMapper());
    }

    @Override
    public void delete(Long id) {
        log.info("DELETE FROM public.user WHERE user_id=" + id);
        jdbcTemplate.update(QueryContainer.DELETE_USER_ACCOUNT_BY_ID, id);
    }

    @Override
    public Iterable<UserAccount> findAll() {
        log.info(QueryContainer.SELECT_ALL_USER_ACCOUNTS);
        return jdbcTemplate.query(QueryContainer.SELECT_ALL_USER_ACCOUNTS, new UseAccountMapper());
    }

}
