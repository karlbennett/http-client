package http.serialisation;

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
public abstract class AbstractDeserialiserTester<T> {

    private Deserialiser<InputStream> deserialiser;
    private InputStream serialisedValue;
    private T deserialisedObject;


    /**
     * Create a new {@code AbstractDeserialiserTester} with a deserialiser and serialised and deserialsed value.
     *
     * @param deserialiser       the deserialiser that will be tested.
     * @param serialisedValue    the serialised value that will be deserialised.
     * @param deserialisedObject the expected deserialised value.
     */
    protected AbstractDeserialiserTester(Deserialiser<InputStream> deserialiser,
                                         InputStream serialisedValue,
                                         T deserialisedObject) {

        this.deserialiser = deserialiser;
        this.serialisedValue = serialisedValue;
        this.deserialisedObject = deserialisedObject;
    }


    @Test
    public void testDeserialiseValue() throws Exception {

        assertEquals("the serialised value should be correctly deserialised.", deserialisedObject,
                deserialiser.deserialise(serialisedValue, deserialisedObject.getClass()));
    }

    @Test
    public void testDeserialiseEmptyValue() throws Exception {

        assertNull("an empty value should be deserialised to null.",
                deserialiser.deserialise(new ByteArrayInputStream("".getBytes()), deserialisedObject.getClass()));
    }

    @Test
    public void testDeserialiseNullValue() throws Exception {

        assertNull("a null value should be deserialised to null.",
                deserialiser.deserialise(null, deserialisedObject.getClass()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeserialiseWithNullType() throws Exception {

        deserialiser.deserialise(serialisedValue, null);
    }
}
