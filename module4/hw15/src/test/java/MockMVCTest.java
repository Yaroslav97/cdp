import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"/application-context.xml"})
public class MockMVCTest {

    private static final String APPLICATION_JSON = "application/json";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.context).build();
    }

    @Test
    public void customHandlerExceptionResolverTest() throws Exception {
        this.mockMvc.perform(get("/hello")
                .accept(APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getEventsByEventTitleTest() throws Exception {
        this.mockMvc.perform(get("/events/JUG")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("events"));
    }

    @Test
    public void getPDFReportTest() throws Exception {
        this.mockMvc.perform(get("/accept=application/pdf/1")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
