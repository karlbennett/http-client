package http.serialisation;

import static http.serialisation.TestDeserialisedObject.*;
import static org.apache.commons.io.IOUtils.toInputStream;

/**
 * @author Karl Bennett
 */
public class FormUrlEncodedBodyDeserialiserTest extends AbstractBodyDeserialiserTester<TestDeserialisedObject> {

    private static final String SERIALISED_VALUE = "id=" + ID + "&name=" + NAME + "&friend=" + FRIEND_ONE + "&friend="
            + FRIEND_TWO + "&friend=" + FRIEND_THREE;


    public FormUrlEncodedBodyDeserialiserTest() {
        super(new FormUrlEncodedDeserialiser(), toInputStream(SERIALISED_VALUE), TEST_DESERIALISED_OBJECT);
    }
}
