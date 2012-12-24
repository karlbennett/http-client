package http.serialisation;

import static http.serialisation.TestDeserialisedObject.*;
import static org.apache.commons.io.IOUtils.toInputStream;

/**
 * @author Karl Bennett
 */
public class XmlDeserialiserTest extends AbstractDeserialiserTester<TestDeserialisedObject> {

    private static final String SERIALISED_VALUE = "<test>" +
                "<id>" + ID + "</id>" +
                "<name>" + NAME + "</name>" +
                "<friends>" +
                    "<friend>" + FRIEND_ONE + "</friend>" +
                    "<friend>" + FRIEND_TWO + "</friend>" +
                    "<friend>" + FRIEND_THREE + "</friend>" +
                "</friends>" +
            "</test>";

    public XmlDeserialiserTest() {
        super(new XmlDeserialiser(), toInputStream(SERIALISED_VALUE), TEST_DESERIALISED_OBJECT);
    }
}
