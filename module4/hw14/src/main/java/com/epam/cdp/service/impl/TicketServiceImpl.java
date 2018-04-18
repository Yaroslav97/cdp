package com.epam.cdp.service.impl;

import com.epam.cdp.dao.impl.TicketDAO;
import com.epam.cdp.model.Event;
import com.epam.cdp.model.Ticket;
import com.epam.cdp.model.User;
import com.epam.cdp.model.impl.TicketImpl;
import com.epam.cdp.service.EventService;
import com.epam.cdp.service.TicketService;
import com.epam.cdp.service.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);

    private TicketDAO ticketDAO;
    private UserAccountService userService;
    private EventService eventService;
    private UserAccountService userAccountService;

    @Autowired
    public TicketServiceImpl(TicketDAO ticketDAO, UserAccountService userService, EventService eventService, UserAccountService userAccountService) {
        this.ticketDAO = ticketDAO;
        this.userService = userService;
        this.eventService = eventService;
        this.userAccountService = userAccountService;
    }

    @Transactional
    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        TicketImpl ticket = new TicketImpl(new Date().getTime(), eventId, userId, category, place);
        ticketDAO.save(ticket);
        userService.withdrawAccount(userId, eventService.getEventById(eventId).getPrice());
        log.info("book ticket for event-" + eventId + ", place-" + place);
        return ticket;
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketDAO.getBookedTickets(user, pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
       return ticketDAO.getBookedTickets(event, pageSize, pageNum);
    }

    @Override
    public void cancelTicket(long ticketId) {
        log.info("Ticket s% canceled", ticketId);
        ticketDAO.delete(ticketId);
    }

    @Override
    public void saveAllTickets(List<Ticket> list) {
        ticketDAO.saveAllTickets(list);
    }

}
