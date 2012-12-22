package http.serialisation;

import http.header.ContentType;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * This is an abstract class that is implemented to test the generic deserialistion behaviour of the different body
 * deserialisers.
 *
 * @author Karl Bennett
 */
public abstract class AbstractBodyDeserialiserTester<C extends ContentType, T> {

    private BodyDeserialiser<InputStream, C> bodyDeserialiser;
    private InputStream serialisedValue;
    private T deserialisedObject;


    /**
     * Create a new {@code AbstractBodyDeserialiserTester} with a deserialiser and serialised and deserialsed value.
     *
     * @param bodyDeserialiser   the deserialiser that will be tested.
     * @param serialisedValue    the serialised value that will be deserialised.
     * @param deserialisedObject the expected deserialised value.
     */
    protected AbstractBodyDeserialiserTester(BodyDeserialiser<InputStream, C> bodyDeserialiser,
                                             InputStream serialisedValue,
                                             T deserialisedObject) {

        this.bodyDeserialiser = bodyDeserialiser;
        this.serialisedValue = serialisedValue;
        this.deserialisedObject = deserialisedObject;
    }


    @Test
    public void testDeserialiseValue() throws Exception {

        assertEquals("the serialised value should be correctly deserialised.", deserialisedObject,
                bodyDeserialiser.deserialise(serialisedValue, deserialisedObject.getClass()));
    }

    @Test
    public void testDeserialiseEmptyValue() throws Exception {

        assertNull("an empty value should be deserialised to null.",
                bodyDeserialiser.deserialise(new ByteArrayInputStream("".getBytes()), deserialisedObject.getClass()));
    }

    @Test
    public void testDeserialiseNullValue() throws Exception {

        assertNull("a null value should be deserialised to null.",
                bodyDeserialiser.deserialise(null, deserialisedObject.getClass()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeserialiseWithNullType() throws Exception {

        bodyDeserialiser.deserialise(serialisedValue, null);
    }
}
