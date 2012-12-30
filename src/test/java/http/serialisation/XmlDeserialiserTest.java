package http.serialisation;

import static http.serialisation.Serialisations.TestDeserialisedObject;
import static http.serialisation.Serialisations.XML_SERIALISED_VALUE;
import static http.serialisation.Serialisations.TEST_DESERIALISED_OBJECT;
import static org.apache.commons.io.IOUtils.toInputStream;

/**
 * @author Karl Bennett
 */
public class XmlDeserialiserTest extends AbstractDeserialiserTester<TestDeserialisedObject> {

    public XmlDeserialiserTest() {
        super(new XmlDeserialiser(), TEST_DESERIALISED_OBJECT, toInputStream(XML_SERIALISED_VALUE));
    }
}
