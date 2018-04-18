package com.epam.cdp.dao.impl;

import com.epam.cdp.dao.CrudRepository;
import com.epam.cdp.model.Event;
import com.epam.cdp.model.Ticket;
import com.epam.cdp.model.User;
import com.epam.cdp.storage.Storage;
import com.epam.cdp.storage.StorageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class TicketDAO implements CrudRepository<Ticket, Long> {

    private Storage<Ticket, Long> storage;
    private StorageUtil<Ticket, Long> storageUtil;

    public void initStorage() {
        storage.patchUpdate(storageUtil.readCSV(StorageUtil.Model.TICKET));
    }

    @Autowired
    public void setStorageUtil(StorageUtil<Ticket, Long> storageUtil) {
        this.storageUtil = storageUtil;
    }

    @Autowired
    public void setStorage(Storage<Ticket, Long> storage) {
        this.storage = storage;
    }

    @Override
    public Ticket save(Ticket ticket) {
        return storage.update(ticket.getId(), ticket);
    }

    @Override
    public Ticket update(Ticket ticket) {
        return storage.update(ticket.getId(), ticket);
    }

    @Override
    public Ticket findOne(Long id) {
        return storage.findOne(id);
    }

    @Override
    public void delete(Long id) {
        storage.delete(id);
    }

    @Override
    public Iterable<Ticket> findAll() {
        return storage.findAll();
    }

    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        List<Ticket> ticketList = (List<Ticket>) storage.findAll();
        return ticketList.stream().filter(u -> u.getUserId() == user.getId())
                .skip(pageNum)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        List<Ticket> ticketList = (List<Ticket>) storage.findAll();
        return ticketList.stream().filter(e -> e.getEventId() == event.getId())
                .skip(pageNum)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

}
