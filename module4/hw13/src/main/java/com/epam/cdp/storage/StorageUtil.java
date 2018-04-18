package com.epam.cdp.storage;

import com.epam.cdp.model.Event;
import com.epam.cdp.model.Ticket;
import com.epam.cdp.model.User;
import com.epam.cdp.model.impl.EventImpl;
import com.epam.cdp.model.impl.TicketImpl;
import com.epam.cdp.model.impl.UserImpl;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StorageUtil<T, ID extends Serializable> {

    private static final String[] USER_STORAGE_MAPPING = {"id", "name", "email"};
    private static final String[] EVENT_STORAGE_MAPPING = {"id", "title", "date"};
    private static final String[] TICKET_STORAGE_MAPPING = {"id", "eventId", "userId", "category", "place"};

    @Value("${user.storage}")
    private String userStorage;

    @Value("${event.storage}")
    private String eventStorage;

    @Value("${ticket.storage}")
    private String ticketStorage;

    public enum Model {USER, EVENT, TICKET}

    private static final Logger LOG = LoggerFactory.getLogger(StorageUtil.class);

    /**
     * Read csv file and return map of users
     *
     * @return map of users
     */
    private Map<ID, T> parser(String[] mapping, String storage, Mapper<T, ID> mapper) {
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(mapping);

        Map<ID, T> map = new HashMap<>();

        try (
                FileReader fileReader = new FileReader(storage);
                CSVParser csvFileParser = new CSVParser(fileReader, csvFileFormat)
        ) {
            List csvRecords = csvFileParser.getRecords();

            map.putAll(mapper.getModel(csvRecords));

            LOG.info("Fetched data from from csv");
        } catch (Exception e) {
            LOG.error("Cannot open ticket storage");
        }
        return map;
    }

    private Map<Long, User> userParser(List csvRecords) {
        Map<Long, User> map = new HashMap<>();

        for (int i = 1; i < csvRecords.size(); i++) {
            CSVRecord record = (CSVRecord) csvRecords.get(i);
            User user = new UserImpl(Long.parseLong(record.get("id")), record.get("name"), record.get("email"));
            map.put(user.getId(), user);
        }
        return map;
    }

    private Map<Long, Event> eventParser(List csvRecords) {
        Map<Long, Event> map = new HashMap<>();

        for (int i = 1; i < csvRecords.size(); i++) {
            CSVRecord record = (CSVRecord) csvRecords.get(i);
            Event event = new EventImpl(Long.parseLong(record.get("id")), record.get("title"), new Date());
            map.put(event.getId(), event);
        }
        return map;
    }

    private Map<Long, Ticket> ticketParser(List csvRecords) {
        Map<Long, Ticket> map = new HashMap<>();

        for (int i = 1; i < csvRecords.size(); i++) {
            CSVRecord record = (CSVRecord) csvRecords.get(i);
            Ticket ticket = new TicketImpl(
                    Long.parseLong(record.get("id")),
                    Long.parseLong(record.get("eventId")),
                    Long.parseLong(record.get("userId")),
                    Ticket.Category.valueOf(record.get("category")),
                    Integer.parseInt((record.get("place")))
            );
            map.put(ticket.getId(), ticket);
        }
        return map;
    }

    public Map<ID, T> readCSV(Model modelName) {
        String[] mapping = null;
        String storage = null;
        Mapper mapper = null;

        switch (modelName) {
            case USER:
                mapping = USER_STORAGE_MAPPING;
                storage = userStorage;
                mapper = this::userParser;
                break;
            case EVENT:
                mapping = EVENT_STORAGE_MAPPING;
                storage = eventStorage;
                mapper = this::eventParser;
                break;
            case TICKET:
                mapping = TICKET_STORAGE_MAPPING;
                storage = ticketStorage;
                mapper = this::ticketParser;
                break;
        }

        return parser(mapping, storage, mapper);
    }

}