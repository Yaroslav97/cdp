package com.epam.cdp.jms.receiver;

import com.epam.cdp.model.Ticket;
import com.epam.cdp.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TicketReceiver {

    private Logger log = LoggerFactory.getLogger(TicketReceiver.class);

    @Autowired
    private TicketService ticketService;

    @JmsListener(destination = "ticket-receiver", containerFactory = "jmsFactory")
    public void receiveMessage(Ticket ticket) {
        log.info("Ticket received - {}", ticket);
        ticketService.bookTicket(ticket.getUserId(), ticket.getEventId(), ticket.getPlace(), ticket.getCategory());
    }

}
