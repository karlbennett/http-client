package http.serialisation;

import http.header.MultipartFormDataContentType;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.apache.commons.io.IOUtils.toInputStream;
import static http.serialisation.MultipartFormDataBodyDeserialiserTest.FormDataTestObject;
import static org.junit.Assert.*;

/**
 * @author Karl Bennett
 */
public class MultipartFormDataBodyDeserialiserTest extends AbstractBodyDeserialiserTester<MultipartFormDataContentType, FormDataTestObject> {

    public static class FormDataTestObject {

        private String name;
        private String fileName;
        private InputStream file;


        public FormDataTestObject(String name, String fileName, InputStream file) {

            this.name = name;
            this.fileName = fileName;
            this.file = file;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public InputStream getFile() {
            return file;
        }

        public void setFile(InputStream file) {
            this.file = file;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            FormDataTestObject that = (FormDataTestObject) o;

            String thisFile = null;
            String thatFile = null;

            try {

                thisFile = IOUtils.toString(this.file);
                thatFile = IOUtils.toString(that.file);

            } catch (IOException e) {

                throw new IllegalStateException(e);
            }

            return !(name != null ? !name.equals(that.name) : that.name != null) &&
                    !(fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) &&
                    !(thisFile != null ? !thisFile.equals(thatFile) : thatFile != null);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
            result = 31 * result + (file != null ? file.hashCode() : 0);
            return result;
        }
    }


    private static final String BOUNDERY = "CA90AE";

    private static final String MULTIPART_HEADER = "Content-Type: multipart/form-data; boundary=" + BOUNDERY + "\n\n";
    private static final String MULTIPART_FOOTER = "--" + BOUNDERY + "--";

    private static final String FILE_NAME = "Test User";
    private static final String FILE_CONTENT = "This is the contents of the test file.\n" +
            "We should have a few lines in this really.\n" +
            "Otherwise it would be far too easy.";

    private static final String NAME_PART = "--" + BOUNDERY + "\n" +
            "Content-Disposition: form-data; name=\"name\"\n" +
            "\n" +
            NAME + "\n";

    private static final String FILE_PART = "--" + BOUNDERY + "\n" +
            "Content-Disposition: form-data; name=\"file\"; filename=\"" + FILE_NAME + "\"\n" +
            "Content-Type: text/plain\n" +
            "\n" +
            FILE_CONTENT + "\n";

    private static final String SERIALISED_VALUE = MULTIPART_HEADER + NAME_PART + FILE_PART + MULTIPART_FOOTER;
    private static final String SERIALISED_FILE = MULTIPART_HEADER + FILE_PART + MULTIPART_FOOTER;

    private static final FormDataTestObject DESERIALISED_OBJECT = new FormDataTestObject(NAME, FILE_NAME,
            toInputStream(FILE_CONTENT));

    private static final MultipartFormDataBodyDeserialiser FORM_DATA_BODY_DESERIALISER = new MultipartFormDataBodyDeserialiser();


    public MultipartFormDataBodyDeserialiserTest() {
        super(FORM_DATA_BODY_DESERIALISER, toInputStream(SERIALISED_VALUE), DESERIALISED_OBJECT);
    }


    @Test
    public void testConvertToInputStream() throws Exception {

        assertEquals("multipart file converted to normal input stream correctly.", FILE_CONTENT,
                IOUtils.toString(FORM_DATA_BODY_DESERIALISER.convertToInputStream(toInputStream(SERIALISED_FILE))));
    }

    @Test
    public void testConvertToByteArray() throws Exception {

        assertArrayEquals("multipart file converted to byte array correctly.", FILE_CONTENT.getBytes(),
                FORM_DATA_BODY_DESERIALISER.convertToByteArray(toInputStream(SERIALISED_FILE)));
    }

    @Test
    public void testConvertToString() throws Exception {

        assertEquals("multipart file converted to string correctly.", FILE_CONTENT,
                FORM_DATA_BODY_DESERIALISER.convertToString(toInputStream(SERIALISED_FILE)));
    }
}
