import com.epam.cdp.facade.BookingFacade;
import com.epam.cdp.model.Ticket;
import com.epam.cdp.model.impl.EventImpl;
import com.epam.cdp.model.impl.UserImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml"})
public class BookingTest {

    @Autowired
    private BookingFacade bookingFacade;

    @Test
    public void createEventTest() {
        bookingFacade.createEvent(new EventImpl(3, "AI", Date.from(Instant.now())));
        Assert.assertNotNull(bookingFacade.getEventById(3));
    }

    @Test
    public void createUserTest() {
        bookingFacade.createUser(new UserImpl(2, "Yaroslav", "yaroslav.poliakov@epam.com"));
        Assert.assertNotNull(bookingFacade.getEventById(2));
    }

    @Test
    public void createTicketTest() {
        bookingFacade.bookTicket(1, 2, 1, Ticket.Category.PREMIUM);
        Assert.assertNotEquals(bookingFacade.getBookedTickets(bookingFacade.getUserById(1), 1, 1), Collections.emptyList());
    }

    @Test
    public void updateEventTest() {
        bookingFacade.createEvent(new EventImpl(4, "AI", Date.from(Instant.now())));
        bookingFacade.updateEvent(new EventImpl(4, "new title", Date.from(Instant.now())));
        Assert.assertEquals(bookingFacade.getEventById(4).getTitle(), "new title");
    }

    @Test
    public void updateUserTest() {
        bookingFacade.createUser(new UserImpl(3, "Yaroslav", "yaroslav@epam.com"));
        bookingFacade.updateUser(new UserImpl(3, "Yaroslav", "yaroslav.poliakov@epam.com"));
        Assert.assertEquals(bookingFacade.getUserByEmail("yaroslav.poliakov@epam.com").getId(), 3);
    }

    @Test
    public void cancelTicketTest() {
        bookingFacade.bookTicket(1, 2, 1, Ticket.Category.PREMIUM);
        bookingFacade.getBookedTickets(bookingFacade.getUserById(1), 5, 1).forEach(v -> bookingFacade.cancelTicket(v.getId()));
        Assert.assertEquals(bookingFacade.getBookedTickets(bookingFacade.getEventById(2), 1, 1), Collections.EMPTY_LIST);
    }

    @Test
    public void deleteEventTest() {
        bookingFacade.createEvent(new EventImpl(5, "title", Date.from(Instant.now())));
        bookingFacade.deleteEvent(5);
        Assert.assertNull(bookingFacade.getEventById(5));
    }

    @Test
    public void deleteUserTest() {
        bookingFacade.createUser(new UserImpl(5, "name", "name@mail.com"));
        bookingFacade.deleteUser(5);
        Assert.assertNull(bookingFacade.getEventById(5));
    }

    @Test
    public void getEventsByDataTest() {
        Date date = Date.from(Instant.now());
        bookingFacade.createEvent(new EventImpl(6, "event", date));
        bookingFacade.createEvent(new EventImpl(8, "event1", date));
        Assert.assertEquals(bookingFacade.getEventsForDay(date, 100, 0).size(), 2);
    }

    @Test
    public void getUsersByNameTest() {
        bookingFacade.createUser(new UserImpl(10, "Name", "name@mail.com"));
        bookingFacade.createUser(new UserImpl(11, "Name", "name1@mail.com"));
        bookingFacade.createUser(new UserImpl(12, "Name", "name2@mail.com"));
        Assert.assertEquals(bookingFacade.getUsersByName("Name", 10, 0).size(), 3);
    }

    @Test
    public void getEventsByTitleTest() {
        bookingFacade.createEvent(new EventImpl(5, "title", Date.from(Instant.now())));
        bookingFacade.createEvent(new EventImpl(6, "title", Date.from(Instant.now())));
        bookingFacade.createEvent(new EventImpl(7, "title", Date.from(Instant.now())));
        Assert.assertEquals(bookingFacade.getEventsByTitle("title", 10, 0).size(), 3);
    }

}
