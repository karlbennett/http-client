package http.serialisation;

import static http.serialisation.Serialisations.*;
import static org.apache.commons.io.IOUtils.toInputStream;

/**
 * @author Karl Bennett
 */
public class XmlSerialiserTest extends AbstractSerialiserTester<TestDeserialisedObject> {

    public XmlSerialiserTest() {
        super(new XmlSerialiser(), toInputStream(XML_SERIALISED_VALUE), TEST_DESERIALISED_OBJECT);
    }
}
