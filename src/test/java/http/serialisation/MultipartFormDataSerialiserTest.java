package http.serialisation;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import static http.serialisation.Serialisations.*;
import static org.apache.commons.io.IOUtils.toInputStream;
import static org.junit.Assert.assertEquals;

/**
 * @author Karl Bennett
 */
public class MultipartFormDataSerialiserTest extends AbstractSerialiserTester<FormDataTestObject> {

    private static final MultipartFormDataSerialiser FORM_DATA_BODY_SERIALISER = new MultipartFormDataSerialiser();


    public MultipartFormDataSerialiserTest() {
        super(FORM_DATA_BODY_SERIALISER, toInputStream(buildMultiPartFormDataSerialisedObject(BOUNDARY)),
                MULTIPART_FORM_DATA_DESERIALISED_OBJECT);
    }


    @Test
    public void testSerialiseString() throws Exception {
        // TODO: Decide on how to name {@code multipart/form-data} string serialisations.
        assertEquals("multipart file converted to string correctly.", buildMultiPartFormDataSerialisedFile(BOUNDARY),
                FORM_DATA_BODY_SERIALISER.serialise(FILE_CONTENT));
    }

    @Test
    public void testSerialiseInputStream() throws Exception {
        // TODO: Decide on how to name {@code multipart/form-data} input stream serialisations.
        assertEquals("multipart file converted to normal input stream correctly.",
                buildMultiPartFormDataSerialisedFile(BOUNDARY),
                IOUtils.toString(FORM_DATA_BODY_SERIALISER.serialise(toInputStream(FILE_CONTENT))));
    }

    @Test
    public void testSerialiseFile() throws Exception {

        assertEquals("multipart file converted to byte array correctly.", buildMultiPartFormDataSerialisedFile(BOUNDARY),
                FORM_DATA_BODY_SERIALISER.serialise(FILE));
    }
}
