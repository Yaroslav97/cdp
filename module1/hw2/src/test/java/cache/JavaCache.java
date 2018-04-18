package cache;

import com.epam.cdp.cashe.Cache;
import com.epam.cdp.cashe.CacheService;
import com.epam.cdp.effective.java.creating.objects.Car;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JavaCache {

    private Cache<Long, Car> cache;

    private Car car1 = new Car.Builder().name("Ferrari").color("red").cabriolet(true).build();
    private Car car2 = new Car.Builder().name("Ferrari").color("Blue").cabriolet(true).build();
    private Car car3 = new Car.Builder().name("Ferrari").color("Blue").cabriolet(true).build();
    private Car car4 = new Car.Builder().name("Mercedes").color("red").cabriolet(false).build();

    @Before
    public void init() throws InterruptedException {
        cache = new CacheService.CasheServiceBuilder<Long, Car>()
                .maxSize(4)
                .expireAfterAccess(100, TimeUnit.MILLISECONDS)
                .build();

        cache.put(1L, car1);
        Thread.sleep(1);
        cache.put(2L, car2);
        Thread.sleep(1);
        cache.put(3L, car3);
        Thread.sleep(1);
        cache.put(4L, car4);
    }

    @Test
    public void cacheOverloadTest() {
        car1.equals(cache.get(1L));
        cache.put(5L, car1);
        assertEquals(4, cache.asMap().size());
        assertTrue(cache.asMap().containsKey(5L));
        assertFalse(!cache.asMap().containsKey(1L));
    }

    @Test
    public void lfuTest() throws InterruptedException {
        cache.get(3L);
        Thread.sleep(50);
        cache.get(3L);
        Thread.sleep(500);
        assertEquals(1, cache.asMap().size());
    }

    @Test
    public void numberOfCashEvictionTest() throws InterruptedException {
        Thread.sleep(500);
        assertEquals(4, cache.evictionCount());
    }

    @Test
    public void averageTimeForPutTest() {
        List<Integer> averageTimeList = new ArrayList<>();

        for (long i = 5; i < 100000; i++) {
            long start = System.currentTimeMillis();
            cache.put(i, new Car.Builder().name("Ferrari" + i).color("red").cabriolet(true).build());
            long finish = System.currentTimeMillis();
            averageTimeList.add((int) (finish - start));
        }

        System.out.println("Average time spent for putting new values into cache - " +
                averageTimeList.stream().mapToInt(Integer::intValue).average() + " milliseconds");
    }

}
