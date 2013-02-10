package http.util;

import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * @author Karl Bennett
 */
public class ParserTest {

    private static final String DELIMITER = ":";

    @Test
    public void testParse() throws Exception {

        String string = "1:2:3:4";

        final Collection<Integer> numbers = new HashSet<Integer>();

        new Parser(string, DELIMITER) {

            @Override
            protected void next(String number) {

                numbers.add(Integer.valueOf(number));
            }
        }.parse();

        assertEquals("the numbers collection should contain four values.", 4, numbers.size());
        assertTrue("the numbers collection should contain 1.", numbers.contains(1));
        assertTrue("the numbers collection should contain 2.", numbers.contains(2));
        assertTrue("the numbers collection should contain 3.", numbers.contains(3));
        assertTrue("the numbers collection should contain 4.", numbers.contains(4));
    }

    @Test
    public void testParseEmptyString() throws Exception {

        new Parser("", DELIMITER) {

            @Override
            protected void next(String number) {

                throw new AssertionError("next should not have been called.");
            }
        }.parse();
    }

    @Test
    public void testParseNullString() throws Exception {

        new Parser(null, DELIMITER) {

            @Override
            protected void next(String number) {

                throw new AssertionError("next should not have been called.");
            }
        }.parse();
    }
}
