package effective_java;

import com.epam.cdp.effective.java.enums.Fruit;
import com.epam.cdp.effective.java.enums.Warehouse;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class WarehouseTest {

    @Test
    public void test() {

        Warehouse warehouse = new Warehouse();
        warehouse.fillFruitMarket();
        assertTrue(warehouse.getFruitMap().containsKey(Fruit.APPLE));
    }
}