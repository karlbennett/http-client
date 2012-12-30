package http.serialisation;

import static http.serialisation.Serialisations.TestDeserialisedObject;
import static org.apache.commons.io.IOUtils.toInputStream;
import static http.serialisation.Serialisations.JSON_SERIALISED_VALUE;
import static http.serialisation.Serialisations.TEST_DESERIALISED_OBJECT;

/**
 * @author Karl Bennett
 */
public class JsonDeserialiserTest extends AbstractDeserialiserTester<TestDeserialisedObject> {

    public JsonDeserialiserTest() {
        super(new JsonDeserialiser(), toInputStream(JSON_SERIALISED_VALUE), TEST_DESERIALISED_OBJECT);
    }
}
