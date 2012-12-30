package http.serialisation;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import static http.serialisation.Serialisations.FormDataTestObject;
import static http.serialisation.Serialisations.MULTIPART_FORM_DATA_SERIALISED_VALUE;
import static http.serialisation.Serialisations.MULTIPART_FORM_DATA_DESERIALISED_OBJECT;
import static http.serialisation.Serialisations.MULTIPART_FORM_DATA_SERIALISED_FILE;
import static http.serialisation.Serialisations.FILE_CONTENT;
import static org.apache.commons.io.IOUtils.toInputStream;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author Karl Bennett
 */
public class MultipartFormDataDeserialiserTest extends AbstractDeserialiserTester<FormDataTestObject> {

    private static final MultipartFormDataDeserialiser FORM_DATA_BODY_DESERIALISER = new MultipartFormDataDeserialiser();


    public MultipartFormDataDeserialiserTest() {
        super(FORM_DATA_BODY_DESERIALISER, toInputStream(MULTIPART_FORM_DATA_SERIALISED_VALUE),
                MULTIPART_FORM_DATA_DESERIALISED_OBJECT);
    }


    @Test
    public void testConvertToInputStream() throws Exception {

        assertEquals("multipart file converted to normal input stream correctly.", FILE_CONTENT,
                IOUtils.toString(FORM_DATA_BODY_DESERIALISER.convertToInputStream(
                        toInputStream(MULTIPART_FORM_DATA_SERIALISED_FILE))));
    }

    @Test
    public void testConvertToByteArray() throws Exception {

        assertArrayEquals("multipart file converted to byte array correctly.", FILE_CONTENT.getBytes(),
                FORM_DATA_BODY_DESERIALISER.convertToByteArray(toInputStream(MULTIPART_FORM_DATA_SERIALISED_FILE)));
    }

    @Test
    public void testConvertToString() throws Exception {

        assertEquals("multipart file converted to string correctly.", FILE_CONTENT,
                FORM_DATA_BODY_DESERIALISER.convertToString(toInputStream(MULTIPART_FORM_DATA_SERIALISED_FILE)));
    }
}
