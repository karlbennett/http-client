package http.serialisation;

import static http.serialisation.Serialisations.*;
import static org.apache.commons.io.IOUtils.toInputStream;

/**
 * @author Karl Bennett
 */
public class FormUrlEncodedSerialiserTest extends AbstractSerialiserTester<TestDeserialisedObject> {

    public FormUrlEncodedSerialiserTest() {
        super(new FormUrlEncodedSerialiser(), toInputStream(FORM_URL_ENCODED_SERIALISED_VALUE),
                TEST_DESERIALISED_OBJECT);
    }
}
