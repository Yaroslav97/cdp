import com.epam.cdp.dao.impl.EventDAO;
import com.epam.cdp.dao.impl.TicketDAO;
import com.epam.cdp.dao.impl.UserAccountDAO;
import com.epam.cdp.dao.impl.UserDAO;
import com.epam.cdp.facade.BookingFacade;
import com.epam.cdp.model.Event;
import com.epam.cdp.model.Ticket;
import com.epam.cdp.model.impl.EventImpl;
import com.epam.cdp.model.impl.TicketImpl;
import com.epam.cdp.model.impl.UserImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml"})
public class FacadeBookingTest {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private EventDAO eventDAO;
    @Autowired
    private TicketDAO ticketDAO;
    @Autowired
    private UserAccountDAO userAccountDAO;
    @Autowired
    private BookingFacade bookingFacade;

    private EmbeddedDatabase embeddedDatabase;

    @Before
    public void setUp() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        embeddedDatabase = builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("sql/schema.sql")
                .addScript("sql/insert-data.sql")
                .build();

        userDAO.setDataSource(embeddedDatabase);
        eventDAO.setDataSource(embeddedDatabase);
        ticketDAO.setDataSource(embeddedDatabase);
        userAccountDAO.setDataSource(embeddedDatabase);
        }

    @Test
    public void bookTicketTest() {
        bookingFacade.refillAccount(0, 150);
        bookingFacade.bookTicket(0, 11, 111, Ticket.Category.PREMIUM);
        bookingFacade.bookTicket(0, 33, 110, Ticket.Category.PREMIUM);
        Assert.assertEquals(0, bookingFacade.getScore(0), 0);
    }

    @Test
    public void bookTicketWithNewDataTest() {
        bookingFacade.createUser(new UserImpl(3, "Yaroslav", "Yaroslav@epam.com"));
        bookingFacade.createEvent(new EventImpl(44, "Tomorrow Land", Date.from(Instant.now()), 150));
        bookingFacade.createEvent(new EventImpl(55, "Microservices with Spring Cloud", Date.from(Instant.now()), 50));
        bookingFacade.refillAccount(3, 200);
        bookingFacade.bookTicket(3, 44, 111, Ticket.Category.PREMIUM);
        bookingFacade.bookTicket(3, 55, 110, Ticket.Category.PREMIUM);
        Assert.assertEquals(0, bookingFacade.getScore(3), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void bookTicketWhenNotEnoughMoney() {
        bookingFacade.bookTicket(2, 11, 1, Ticket.Category.PREMIUM);
    }

    @Test
    public void createAndUpdateUserTest() {
        bookingFacade.createUser(new UserImpl(3, "Yaroslav", "none"));
        bookingFacade.updateUser(new UserImpl(3, "Yaroslav", "Yaroslav@epam.com"));
        Assert.assertEquals(3, bookingFacade.getUserByEmail("Yaroslav@epam.com").getId());
    }

    @Test
    public void createAndUpdateEventTest() {
        bookingFacade.createEvent(new EventImpl(100, "JUG#Kharkiv", Date.from(Instant.now()), 200));

        Event event = bookingFacade.getEventsByTitle("JUG#Kharkiv", 1, 0).get(0);
        event.setPrice(100);
        bookingFacade.updateEvent(event);

        Assert.assertEquals(100, bookingFacade.getEventById(100).getPrice(), 0);
    }

    @Test
    public void bookTicketAndCancel() {
        bookingFacade.createEvent(new EventImpl(100, "JUG#Kharkiv", Date.from(Instant.now()), 200));
        bookingFacade.createUser(new UserImpl(1111, "Yaroslav", "Yaroslav@epam.com"));
        bookingFacade.refillAccount(1111, 200);
        bookingFacade.bookTicket(1111, 100, 21, Ticket.Category.PREMIUM);

        Ticket ticket = bookingFacade.getBookedTickets(bookingFacade.getUserById(1111), 1, 0).get(0);
        bookingFacade.cancelTicket(ticket.getId());

        Assert.assertEquals(Collections.EMPTY_LIST, bookingFacade.getBookedTickets(bookingFacade.getEventById(100), 1, 0));
    }

    @After
    public void tearDown() {
        embeddedDatabase.shutdown();
    }

}
