package http.serialisation;

import http.header.XmlContentType;

import static http.serialisation.TestDeserialisedObject.*;
import static org.apache.commons.io.IOUtils.toInputStream;

/**
 * @author Karl Bennett
 */
public class XmlBodyDeserialiserTest extends AbstractBodyDeserialiserTester<XmlContentType, TestDeserialisedObject> {

    private static final String SERIALISED_VALUE = "<test>" +
                "<id>" + ID + "</id>" +
                "<name>" + NAME + "</name>" +
                "<friends>" +
                    "<friend>" + FRIEND_ONE + "</friend>" +
                    "<friend>" + FRIEND_TWO + "</friend>" +
                    "<friend>" + FRIEND_THREE + "</friend>" +
                "</friends>" +
            "</test>";

    public XmlBodyDeserialiserTest() {
        super(new XmlBodyDeserialiser(), toInputStream(SERIALISED_VALUE), TEST_DESERIALISED_OBJECT);
    }
}
