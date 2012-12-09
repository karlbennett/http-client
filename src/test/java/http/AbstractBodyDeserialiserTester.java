package http;

import http.header.ContentType;
import http.serialisation.BodyDeserialiser;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * This is an abstract class that is implemented to test the generic deserialistion behaviour of the different body
 * deserialisers.
 *
 * @author Karl Bennett
 */
public abstract class AbstractBodyDeserialiserTester<C extends ContentType, T> {

    public static final Long ID = 1L;
    public static final String NAME = "Test User";
    public static final String FRIEND_ONE = "First Friend";
    public static final String FRIEND_TWO = "Second Friend";
    public static final String FRIEND_THREE = "Third Friend";
    public static final Collection<String> FRIENDS = Arrays.asList(FRIEND_ONE, FRIEND_TWO, FRIEND_THREE);

    public static final TestDeserialisedObject TEST_DESERIALISED_OBJECT = new TestDeserialisedObject(ID, NAME, FRIENDS);


    public static class TestDeserialisedObject {

        private Long id;
        private String name;
        private Collection<String> friends;


        public TestDeserialisedObject(Long id, String name, Collection<String> friends) {

            this.id = id;
            this.name = name;
            this.friends = friends;
        }


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Collection<String> getFriends() {
            return friends;
        }

        public void setFriends(Collection<String> friends) {
            this.friends = friends;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestDeserialisedObject that = (TestDeserialisedObject) o;

            return !(name != null ? !name.equals(that.name) : that.name != null) &&
                    !(id != null ? !id.equals(that.id) : that.id != null) &&
                    !(friends != null ? !friends.equals(that.friends) : that.friends != null);
        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            result = 31 * result + (friends != null ? friends.hashCode() : 0);
            return result;
        }
    }


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
