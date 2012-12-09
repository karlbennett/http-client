package http.serialisation;

import http.header.FormUrlEncodedContentType;

import static http.serialisation.AbstractBodyDeserialiserTester.TestDeserialisedObject;
import static org.apache.commons.io.IOUtils.toInputStream;

/**
 * @author Karl Bennett
 */
public class FormUrlEncodedBodyDeserialiserTest extends AbstractBodyDeserialiserTester<FormUrlEncodedContentType, TestDeserialisedObject> {

    private static final String SERIALISED_VALUE = "id=" + ID + "&name=" + NAME + "&friend=" + FRIEND_ONE + "&friend="
            + FRIEND_TWO + "&friend=" + FRIEND_THREE;


    protected FormUrlEncodedBodyDeserialiserTest() {
        super(new FormUrlEncodedBodyDeserialiser(), toInputStream(SERIALISED_VALUE), TEST_DESERIALISED_OBJECT);
    }
}
