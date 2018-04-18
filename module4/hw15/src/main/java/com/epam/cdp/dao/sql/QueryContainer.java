package com.epam.cdp.dao.sql;

public class QueryContainer {

    public static final String INSERT_USER = "INSERT INTO public.user(id, name, email) VALUES (?, ?, ?);";
    public static final String INSERT_EVENT = "INSERT INTO public.event(id, title, date, price) VALUES (?, ?, ?, ?);";
    public static final String INSERT_USER_ACCOUNT = "INSERT INTO public.user_account(user_id, score) VALUES (?, ?);";
    public static final String INSERT_TICKET = "INSERT INTO public.ticket(id, event_id, user_id, category, place) VALUES (?, ?, ?, ?, ?);";

    public static final String UPDATE_USER_BY_ID = "UPDATE public.user SET name=?, email=? WHERE id=?;";
    public static final String UPDATE_EVENT_BY_ID = "UPDATE public.event SET title=?, date=?, price=? WHERE id=?;";
    public static final String UPDATE_USER_ACCOUNT_BY_ID = "UPDATE public.user_account SET score=? WHERE user_id=?;";
    public static final String UPDATE_TICKET_BY_ID = "UPDATE public.ticket SET event_id=?, user_id=?, category=?, place=? WHERE id=?;";

    public static final String SELECT_USER_BY_ID = "SELECT * FROM public.user WHERE id=?;";
    public static final String SELECT_EVENT_BY_ID = "SELECT * FROM public.event WHERE id=?;";
    public static final String SELECT_USER_ACCOUNT_BY_ID = "SELECT * FROM public.user_account WHERE user_id=?;";
    public static final String SELECT_TICKET_BY_ID = "SELECT * FROM public.ticket WHERE user_id=?;";

    public static final String SELECT_ALL_USERS = "SELECT * FROM public.user;";
    public static final String SELECT_ALL_EVENTS = "SELECT * FROM public.event;";
    public static final String SELECT_ALL_USER_ACCOUNTS = "SELECT * FROM public.user_account;";
    public static final String SELECT_ALL_TICKETS = "SELECT * FROM public.ticket;";

    public static final String DELETE_USER_BY_ID = "DELETE FROM public.user WHERE id=?;";
    public static final String DELETE_EVENT_BY_ID = "DELETE FROM public.event WHERE id=?;";
    public static final String DELETE_USER_ACCOUNT_BY_ID = "DELETE FROM public.user_account WHERE user_id=?;";
    public static final String DELETE_TICKET_BY_ID = "DELETE FROM public.ticket WHERE id=?;";

    public static final String SELECT_USER_BY_EMAIL = "SELECT * FROM public.user WHERE email=?;";
    public static final String SELECT_USERS_BY_NAME = "SELECT * FROM public.user WHERE name=? OFFSET ? LIMIT ?;";
    public static final String SELECT_EVENTS_BY_TITLE = "SELECT * FROM public.event WHERE title=? OFFSET ? LIMIT ?;";
    public static final String SELECT_EVENTS_BY_DAY = "SELECT * FROM public.event WHERE date=? OFFSET ? LIMIT ?;";
    public static final String SELECT_TICKETS_BY_USER = "SELECT * FROM public.ticket WHERE user_id=? OFFSET ? LIMIT ?;";
    public static final String SELECT_TICKETS_BY_EVENT = "SELECT * FROM public.ticket WHERE event_id=? OFFSET ? LIMIT ?;";

}
