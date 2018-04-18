import com.epam.cdp.dao.impl.EmployeeDAO;
import com.epam.cdp.util.HibernateSession;
import net.sf.ehcache.CacheManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.SessionStatistics;
import org.hibernate.stat.Statistics;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CashTest {

    @Test
    public void test() throws Exception {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        SessionStatistics sessionStatistics = session.getStatistics();
        Statistics statistics = sessionFactory.getStatistics();
        statistics.setStatisticsEnabled(true);

        EmployeeDAO employeeDAO = new EmployeeDAO(sessionFactory);
        employeeDAO.findByID((long) 2);
        employeeDAO.findByID((long) 2);
        employeeDAO.findByID((long) 2);
        employeeDAO.findByID((long) 2);

        System.out.println("miss: " + statistics.getQueryCacheHitCount());
        System.out.println("hit: " + statistics.getQueryCacheMissCount());
        System.out.println(statistics);

        int size = CacheManager.ALL_CACHE_MANAGERS.get(0).getCache("com.epam.cdp.model.Employee").getSize();
        assertEquals(size, 1);
    }

}
