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
        Collection<String> sums = new HashSet<String>(Arrays.asList("1", "3", "10"));

        Collection<String> results = new NullSafeForEach<Integer, String>(numbers) {

            @Override
            protected String next(Integer number) {

                sum[0] += number;

                return 3 == number ? null : String.valueOf(sum[0]);
            }
        }.results();

        assertEquals("the sum should be correct.", 10, sum[0]);
        assertEquals("the results should be correct.", sums, results);
    }

    @Test
    public void testNullSafeForEachWithArray() throws Exception {

        final int[] sum = {0};

        Collection<String> sums = new HashSet<String>(Arrays.asList("1", "3", "10"));

        Collection<String> results = new NullSafeForEach<Integer, String>(new Integer[]{1, 2, 3, 4}) {

            @Override
            protected String next(Integer number) {

                sum[0] += number;

                return 3 == number ? null : String.valueOf(sum[0]);
            }
        }.results();

        assertEquals("the sum should be correct.", 10, sum[0]);
        assertEquals("the results should be correct.", sums, results);
    }

    @Test
    public void testNullSafeForEachWithMap() throws Exception {

        final int[] sum = {0};

        Map<String, Integer> numbers = new TreeMap<String, Integer>();
        numbers.put("one", 1);
        numbers.put("two", 2);
        numbers.put("three", 3);
        numbers.put("four", 4);

        Collection<String> results = new NullSafeForEach<Entry<String, Integer>, String>(numbers.entrySet()) {

            @Override
            protected String next(Entry<String, Integer> number) {

                sum[0] += number.getValue();

                return number.getKey();
            }
        }.results();

        assertEquals("the sum should be correct.", 10, sum[0]);
        assertEquals("the results should be correct.", numbers.keySet(), results);
    }

    @Test
    public void testAddAll() throws Exception {

        final int[] sum = {0};

        Collection<Integer> numbers = Arrays.asList(1, 2, 3, 4);

        Collection<String> sums = new HashSet<String>(){{
            addAll(Arrays.asList("11"));
            addAll(Arrays.asList("21", "23"));
            addAll(Arrays.asList("31", "33", "36"));
            addAll(Arrays.asList("41", "43", "46", "410"));
        }};

        final Collection<String> sumHolder = new HashSet<String>();
        Collection<String> results = new NullSafeForEach<Integer, String>(numbers) {

            @Override
            protected String next(Integer number) {

                sum[0] += number;
                sumHolder.add(String.valueOf(sum[0]));

                Collection<String> numbers = new HashSet<String>();

                for (String sumString : sumHolder) {

                    numbers.add(number.toString() + sumString);
                }

                addAll(numbers);

                return null;
            }
        }.results();

        assertEquals("the sum should be correct.", 10, sum[0]);
        assertEquals("the results should be correct.", sums, results);
    }

    @Test
    public void testNullSafeForEachWithEmptyCollection() throws Exception {

        new NullSafeForEach<Void, Void>(Collections.<Void>emptySet()) {

            @Override
            protected Void next(Void number) {

                throw new AssertionError("NullSafeForEach.next(T) should not be called.");
            }
        };
    }

    @Test
    public void testNullSafeForEachWithEmptyArray() throws Exception {

        new NullSafeForEach<Void, Void>(new Void[0]) {

            @Override
            protected Void next(Void number) {

                throw new AssertionError("NullSafeForEach.next(T) should not be called.");
            }
        };
    }

    @Test
    public void testNullSafeForEachWithNullCollection() throws Exception {

        new NullSafeForEach<Void, Void>((Collection<Void>) null) {

            @Override
            protected Void next(Void number) {

                throw new AssertionError("NullSafeForEach.next(T) should not be called.");
            }
        };
    }

    @Test
    public void testNullSafeForEachWithNullArray() throws Exception {

        new NullSafeForEach<Void, Void>((Void[]) null) {

            @Override
            protected Void next(Void number) {

                throw new AssertionError("NullSafeForEach.next(T) should not be called.");
            }
        };
    }
}
