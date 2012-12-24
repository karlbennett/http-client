package http.serialisation;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * This is an abstract class that is implemented to test the generic serialisation behaviour of the different body
 * serialisers.
 *
 * @author Karl Bennett
 */
public abstract class AbstractSerialiserTester<T> {


    private Serialiser<InputStream> serialiser;
    private InputStream serialisedValue;
    private T object;


    /**
     * Create a new {@code AbstractSerialiserTester} with a serialiser and a deserialised and serialised value.
     *
     * @param serialiser  the serialiser that will be tested.
     * @param object          the object that will be serialized.
     * @param serialisedValue the serialised value that should be produced.
     */
    protected AbstractSerialiserTester(Serialiser<InputStream> serialiser,
                                       T object, InputStream serialisedValue) {

        this.serialiser = serialiser;
        this.serialisedValue = serialisedValue;
        this.object = object;
    }


    @Test
    public void testSerialiseValue() throws Exception {

        assertEquals("the object should be correctly serialised.", IOUtils.toString(serialisedValue),
                IOUtils.toString(serialiser.serialise(object)));
    }

    @Test
    public void testSerialiseNullValue() throws Exception {

        assertEquals("a null value should be serialised to an empty string.", "",
                IOUtils.toString(serialiser.serialise(null)));
    }
}
