package cache;

import com.epam.cdp.effective.java.creating.objects.Car;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class GoogleCache {

    private static final Logger LOG = LoggerFactory.getLogger(GoogleCache.class);

    private LoadingCache<Long, Car> cache;

    private Car car1 = new Car.Builder().name("Ferrari").color("red").cabriolet(true).build();
    private Car car2 = new Car.Builder().name("Ferrari").color("Blue").cabriolet(true).build();
    private Car car3 = new Car.Builder().name("Ferrari").color("Blue").cabriolet(true).build();
    private Car car4 = new Car.Builder().name("Mercedes").color("black").cabriolet(true).build();

    @Before
    public void init() throws InterruptedException {
        cache = CacheBuilder.newBuilder()
                .expireAfterAccess(20, TimeUnit.MILLISECONDS)
                .maximumSize(4)
                .recordStats()
                .concurrencyLevel(10)
                .removalListener((RemovalListener<Long, Car>) notification -> LOG.info("Item removed - " + notification.getKey()))
                .build(new CacheLoader<Long, Car>() {
                    @Override
                    public Car load(Long key) {
                        return getData(key);
                    }
                });

        cache.put(1L, car1);
        cache.put(2L, car2);
        cache.put(3L, car3);
        cache.put(4L, car4);
    }

    @Test
    public void test() throws InterruptedException, ExecutionException {
        assertEquals(4, cache.asMap().size());
    }

    @Test
    public void evictionCountTest() throws InterruptedException {
        Thread.sleep(5);
        cache.put(5L, car1);
        Thread.sleep(5);

        assertEquals(1, cache.stats().evictionCount());
    }

    @Test
    public void averageTimeForPutTest() {

        for (long i = 5; i < 10000; i++) {
            cache.put(i, new Car.Builder().name("Ferrari" + i).color("red").cabriolet(true).build());
        }

        System.out.println("Average time spent for putting new values into cache - " +
                cache.stats().averageLoadPenalty() + " milliseconds");
    }

    private static Car getData(long id) {
        Car car1 = new Car.Builder().name("Ferrari").color("red").cabriolet(true).build();
        Car car2 = new Car.Builder().name("Ferrari").color("blue").cabriolet(true).build();
        Car car3 = new Car.Builder().name("Ferrari").color("black").cabriolet(true).build();

        Map<Long, Car> database = new HashMap<>();

        database.put(11L, car1);
        database.put(22L, car2);
        database.put(33L, car3);

        return database.get(id);
    }

}
