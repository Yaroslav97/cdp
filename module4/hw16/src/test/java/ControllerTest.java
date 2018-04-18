import com.epam.cdp.Application;
import com.epam.cdp.dao.impl.EventDAO;
import com.epam.cdp.dao.impl.TicketDAO;
import com.epam.cdp.dao.impl.UserAccountDAO;
import com.epam.cdp.dao.impl.UserDAO;
import com.epam.cdp.facade.BookingFacade;
import com.epam.cdp.model.Event;
import com.epam.cdp.model.Ticket;
import com.epam.cdp.model.impl.EventImpl;
import com.epam.cdp.model.impl.TicketImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application.properties")
@ContextConfiguration(classes = Application.class)
public class ControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private BookingFacade bookingFacade;

    @MockBean
    private EventDAO eventDAO;

    @MockBean
    private UserDAO userDAO;

    @MockBean
    private UserAccountDAO userAccountDAO;

    @MockBean
    private TicketDAO ticketDAO;

    @MockBean
    private JmsTemplate jmsTemplate;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void createEvent() throws Exception {
        when(eventDAO.save(any(Event.class))).thenReturn(new EventImpl());
        String eventJson = OBJECT_MAPPER.writeValueAsString(new EventImpl());
        mvc.perform(post("/event").contentType(MediaType.APPLICATION_JSON)
                .content(eventJson)).andExpect(status().isOk());
        verify(eventDAO).save(any(EventImpl.class));
    }

    @Test

    public void updateEvent() throws Exception {
        when(eventDAO.update(any(Event.class))).thenReturn(new EventImpl());
        Event event = new EventImpl();
        event.setId(1L);
        String eventJson = OBJECT_MAPPER.writeValueAsString(event);
        mvc.perform(put("/event").contentType(MediaType.APPLICATION_JSON)
                .content(eventJson)).andExpect(status().isOk());
        verify(eventDAO).update(any(EventImpl.class));
    }

    @Test
    public void bookTicketAsync() throws Exception {
        Ticket ticket = new TicketImpl();
        ticket.setCategory(Ticket.Category.PREMIUM);

        String ticketJson = OBJECT_MAPPER.writeValueAsString(ticket);

        mvc.perform(post("/ticket/book/async").contentType(MediaType.APPLICATION_JSON)
                .content(ticketJson)).andExpect(status().isOk());
        verify(jmsTemplate).convertAndSend(anyString(), any(Object.class));
    }

    @Test
    public void bookTicketAsync1() throws Exception {
        ReflectionTestUtils.setField(bookingFacade, "jmsTemplate", jmsTemplate);
        Ticket ticket = new TicketImpl();
        ticket.setCategory(Ticket.Category.PREMIUM);
        String ticketJson = OBJECT_MAPPER.writeValueAsString(ticket);
        mvc.perform(post("/ticket/book/async").contentType(MediaType.APPLICATION_JSON)
                .content(ticketJson)).andExpect(status().isOk());
        verify(jmsTemplate).convertAndSend(anyString(), any(Object.class));
    }

    @Test
    public void bookTicketAsync2() throws Exception {
        Ticket ticket = new TicketImpl();
        ticket.setCategory(Ticket.Category.PREMIUM);
        String ticketJson = OBJECT_MAPPER.writeValueAsString(ticket);
        mvc.perform(post("/ticket/book/async").contentType(MediaType.APPLICATION_JSON)
                .content(ticketJson)).andExpect(status().isOk());
        verify(jmsTemplate).convertAndSend(anyString(), any(Object.class));
    }

}
