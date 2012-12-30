package http.serialisation;

import static org.apache.commons.io.IOUtils.toInputStream;
import static http.serialisation.Serialisations.TestDeserialisedObject;
import static http.serialisation.Serialisations.FORM_URL_ENCODED_SERIALISED_VALUE;
import static http.serialisation.Serialisations.TEST_DESERIALISED_OBJECT;

/**
 * @author Karl Bennett
 */
public class FormUrlEncodedDeserialiserTest extends AbstractDeserialiserTester<TestDeserialisedObject> {

    public FormUrlEncodedDeserialiserTest() {
        super(new FormUrlEncodedDeserialiser(), TEST_DESERIALISED_OBJECT, toInputStream(FORM_URL_ENCODED_SERIALISED_VALUE)
        );
    }
}
