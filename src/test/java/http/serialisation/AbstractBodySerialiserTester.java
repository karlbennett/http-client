package http.serialisation;

import http.header.ContentType;
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
public abstract class AbstractBodySerialiserTester<C extends ContentType, T> {


    private BodySerialiser<InputStream, C> bodySerialiser;
    private InputStream serialisedValue;
    private T object;


    /**
     * Create a new {@code AbstractBodySerialiserTester} with a serialiser and a deserialised and serialised value.
     *
     * @param bodySerialiser  the serialiser that will be tested.
     * @param object          the object that will be serialized.
     * @param serialisedValue the serialised value that should be produced.
     */
    protected AbstractBodySerialiserTester(BodySerialiser<InputStream, C> bodySerialiser,
                                           T object, InputStream serialisedValue) {

        this.bodySerialiser = bodySerialiser;
        this.serialisedValue = serialisedValue;
        this.object = object;
    }


    @Test
    public void testSerialiseValue() throws Exception {

        assertEquals("the object should be correctly serialised.", IOUtils.toString(serialisedValue),
                IOUtils.toString(bodySerialiser.serialise(object)));
    }

    @Test
    public void testSerialiseNullValue() throws Exception {

        assertEquals("a null value should be serialised to an empty string.", "",
                IOUtils.toString(bodySerialiser.serialise(null)));
    }
}
