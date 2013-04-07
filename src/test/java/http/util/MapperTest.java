package http.util;

import org.junit.Test;

import java.util.*;

import static java.util.Map.Entry;
import static org.junit.Assert.assertEquals;

/**
 * @author Karl Bennett
 */
public class MapperTest {


    @Test
    public void testMapperWithCollection() throws Exception {

        final int[] sum = {0};

        Collection<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        List<String> sums = new ArrayList<String>(Arrays.asList("1", "3", "10"));

        List<String> results = new Mapper<Integer, String>(numbers) {

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
    public void testMapperWithArray() throws Exception {

        final int[] sum = {0};

        List<String> sums = new ArrayList<String>(Arrays.asList("1", "3", "10"));

        List<String> results = new Mapper<Integer, String>(new Integer[]{1, 2, 3, 4}) {

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
    public void testMapperWithMap() throws Exception {

        final int[] sum = {0};

        Map<String, Integer> numbers = new TreeMap<String, Integer>();
        numbers.put("one", 1);
        numbers.put("two", 2);
        numbers.put("three", 3);
        numbers.put("four", 4);

        List<String> results = new Mapper<Entry<String, Integer>, String>(numbers.entrySet()) {

            @Override
            protected String next(Entry<String, Integer> number) {

                sum[0] += number.getValue();

                return number.getKey();
            }
        }.results();

        assertEquals("the sum should be correct.", 10, sum[0]);
        assertEquals("the results should be correct.", new ArrayList<String>(numbers.keySet()), results);
    }

    @Test
    public void testAddAll() throws Exception {

        final int[] sum = {0};

        Collection<Integer> numbers = Arrays.asList(1, 2, 3, 4);

        List<String> sums = new ArrayList<String>() {{
            addAll(Arrays.asList("11"));
            addAll(Arrays.asList("21", "23"));
            addAll(Arrays.asList("31", "33", "36"));
            addAll(Arrays.asList("41", "43", "46", "410"));
        }};

        final List<String> sumHolder = new ArrayList<String>();
        List<String> results = new Mapper<Integer, String>(numbers) {

            @Override
            protected String next(Integer number) {

                sum[0] += number;
                sumHolder.add(String.valueOf(sum[0]));

                List<String> numbers = new ArrayList<String>();

                for (String sumString : sumHolder) {

                    numbers.add(number.toString() + sumString);
                }

                addAll(numbers);

                return null;
            }
        }.results(sums.size());

        assertEquals("the sum should be correct.", 10, sum[0]);
        assertEquals("the results should be correct.", sums, results);
    }

    @Test
    public void testMapperWithEmptyCollection() throws Exception {

        new Mapper<Void, Void>(Collections.<Void>emptySet()) {

            @Override
            protected Void next(Void number) {

                throw new AssertionError("Mapper.next(T) should not be called.");
            }
        };
    }

    @Test
    public void testMapperWithEmptyArray() throws Exception {

        new Mapper<Void, Void>(new Void[0]) {

            @Override
            protected Void next(Void number) {

                throw new AssertionError("Mapper.next(T) should not be called.");
            }
        }.results();
    }

    @Test
    public void testMapperWithNullCollection() throws Exception {

        new Mapper<Void, Void>((Collection<Void>) null) {

            @Override
            protected Void next(Void number) {

                throw new AssertionError("Mapper.next(T) should not be called.");
            }
        }.results();
    }

    @Test
    public void testMapperWithNullArray() throws Exception {

        new Mapper<Void, Void>((Void[]) null) {

            @Override
            protected Void next(Void number) {

                throw new AssertionError("Mapper.next(T) should not be called.");
            }
        }.results();
    }
}
