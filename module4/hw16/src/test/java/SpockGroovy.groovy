import com.epam.cdp.dao.impl.EventDAO
import com.epam.cdp.model.Event
import org.springframework.jdbc.core.JdbcTemplate
import spock.lang.Specification

import javax.sql.DataSource
import javax.swing.tree.RowMapper

class SpockGroovy extends Specification {

    private EventDAO eventDAO
    private JdbcTemplate jdbcTemplate
    private DataSource dataSource

    def setup() {
        jdbcTemplate = Mock()
        dataSource = Mock()
        eventDAO = new EventDAO()
        eventDAO.setDataSource(dataSource)
    }


    def "It should have possibility to get events for day"() {
        given:
        Date date = new Date(System.nanoTime())
        List<Event> expectedEvents = Collections.emptyList()
        jdbcTemplate.query(_ as String, _ as Object[], _ as RowMapper<Event>) >> Collections.emptyList()
        when:
        List<Event> eventsForDay = eventDAO.getEventsForDay(date, 10, 0)
        then:
        expectedEvents == eventsForDay
    }
}
