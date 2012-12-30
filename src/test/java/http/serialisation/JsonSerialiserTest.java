package http.serialisation;

import static http.serialisation.Serialisations.*;
import static org.apache.commons.io.IOUtils.toInputStream;

/**
 * @author Karl Bennett
 */
public class JsonSerialiserTest extends AbstractSerialiserTester<TestDeserialisedObject> {

    public JsonSerialiserTest() {
        super(new JsonSerialiser(), toInputStream(JSON_SERIALISED_VALUE), TEST_DESERIALISED_OBJECT);
    }
}
