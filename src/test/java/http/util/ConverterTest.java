package http.util;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static http.util.Converter.Conversion;
import static org.junit.Assert.assertEquals;

/**
 * @author Karl Bennett
 */
public class ConverterTest {

    private static class TestObject {

        @Override
        public boolean equals(Object object) {

            return this.getClass().equals(object.getClass());
        }
    }

    private static final Map<Class, Conversion> CONVERSIONS;

    static {

        Map<Class, Conversion> conversions = new HashMap<Class, Conversion>();
        conversions.put(TestObject.class, new Conversion<TestObject, Object>() {

            @Override
            public TestObject convert(Object object) {

                return new TestObject();
            }
        });

        CONVERSIONS = conversions;
    }

    private Converter converter;


    @Before
    public void setUp() throws Exception {

        converter = new Converter(CONVERSIONS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateConverterWithNullMap() throws Exception {

        new Converter(null);
    }

    @Test
    public void testConvert() throws Exception {

        assertEquals("attribute one should be converted.", new TestObject(), converter.convert(TestObject.class,
                new Object()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertWithUnsupportedType() throws Exception {

        converter.convert(Integer.class, new Object());
    }
}
