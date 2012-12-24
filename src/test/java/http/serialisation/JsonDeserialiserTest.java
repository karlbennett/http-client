package http.serialisation;

import static http.serialisation.TestDeserialisedObject.*;
import static org.apache.commons.io.IOUtils.toInputStream;

/**
 * @author Karl Bennett
 */
public class JsonDeserialiserTest extends AbstractDeserialiserTester<TestDeserialisedObject> {

    private static final String SERIALISED_VALUE = "{" +
                "\"id\":" + ID + "," +
                "\"name\":" + NAME + "," +
                "\"friends\":[" +
                    FRIEND_ONE + "," +
                    FRIEND_TWO + "," +
                    FRIEND_THREE +
                "]" +
            "}";


    public JsonDeserialiserTest() {
        super(new JsonDeserialiser(), toInputStream(SERIALISED_VALUE), TEST_DESERIALISED_OBJECT);
    }
}
