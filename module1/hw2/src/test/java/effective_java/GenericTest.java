package effective_java;

import com.epam.cdp.effective.java.generics.GenericList;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class GenericTest {

    @Test
    public void sortGenericListTest() {
        GenericList<Integer> genericList = new GenericList<>();

        genericList.put(1);
        genericList.put(2);
        genericList.put(4);
        genericList.put(3);
        genericList.put(5);

        assertEquals(Arrays.asList(1, 2, 3, 4, 5), genericList.getSortedList());
    }
}
