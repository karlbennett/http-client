package http.util;

import org.junit.Test;

import java.util.*;

import static java.util.Map.Entry;
import static org.junit.Assert.assertEquals;

/**
 * @author Karl Bennett
 */
public class NullSafeForEachTest {


    @Test
    public void testNullSafeForEachWithCollection() throws Exception {

        final int[] sum = {0};

        Collection<Integer> numbers = Arrays.asList(1, 2, 3, 4);

        new NullSafeForEach<Integer>(numbers) {

            @Override
            protected void next(Integer number) {

                sum[0] += number;
            }
        };

        assertEquals("the sum should be correct.", 10, sum[0]);
    }

    @Test
    public void testNullSafeForEachWithArray() throws Exception {

        final int[] sum = {0};

        new NullSafeForEach<Integer>(new Integer[]{1, 2, 3, 4}) {

            @Override
            protected void next(Integer number) {

                sum[0] += number;
            }
        };

        assertEquals("the sum should be correct.", 10, sum[0]);
    }

    @Test
    public void testNullSafeForEachWithMap() throws Exception {

        final StringBuilder builder = new StringBuilder();
        final int[] sum = {0};

        Map<String, Integer> numbers = new TreeMap<String, Integer>();
        numbers.put("one", 1);
        numbers.put("two", 2);
        numbers.put("three", 3);
        numbers.put("four", 4);

        new NullSafeForEach<Entry<String, Integer>>(numbers.entrySet()) {

            @Override
            protected void next(Entry<String, Integer> number) {

                builder.append(number.getKey());
                sum[0] += number.getValue();
            }
        };

        assertEquals("the string should be correct.", "fouronethreetwo", builder.toString());
        assertEquals("the sum should be correct.", 10, sum[0]);
    }

    @Test
    public void testNullSafeForEachWithEmptyCollection() throws Exception {

        new NullSafeForEach<Object>(Collections.emptySet()) {

            @Override
            protected void next(Object number) {

                throw new AssertionError("NullSafeForEach.next(T) should not be called.");
            }
        };
    }

    @Test
    public void testNullSafeForEachWithEmptyArray() throws Exception {

        new NullSafeForEach<Object>(new Object[0]) {

            @Override
            protected void next(Object number) {

                throw new AssertionError("NullSafeForEach.next(T) should not be called.");
            }
        };
    }

    @Test
    public void testNullSafeForEachWithNullCollection() throws Exception {

        new NullSafeForEach<Object>((Iterable<Object>) null) {

            @Override
            protected void next(Object number) {

                throw new AssertionError("NullSafeForEach.next(T) should not be called.");
            }
        };
    }

    @Test
    public void testNullSafeForEachWithNullArray() throws Exception {

        new NullSafeForEach<Object>((Object[]) null) {

            @Override
            protected void next(Object number) {

                throw new AssertionError("NullSafeForEach.next(T) should not be called.");
            }
        };
    }
}
