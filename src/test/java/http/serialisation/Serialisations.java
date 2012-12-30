package http.serialisation;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;

import static org.apache.commons.io.IOUtils.toInputStream;

/**
 * This is a utility class that holds the common types and constants for the serialisation tests.
 *
 * @author Karl Bennett
 */
public final class Serialisations {

    private Serialisations() {
    }


    /**
     * A class that can be used as a container to deserialise test data into. It also contains constant test values.
     *
     * @author Karl Bennett
     */
    public static class TestDeserialisedObject {

        private Long id;
        private String name;
        private Collection<String> friends;


        public TestDeserialisedObject(Long id, String name, Collection<String> friends) {

            this.id = id;
            this.name = name;
            this.friends = friends;
        }


        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Collection<String> getFriends() {
            return friends;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestDeserialisedObject that = (TestDeserialisedObject) o;

            return !(name != null ? !name.equals(that.name) : that.name != null) &&
                    !(id != null ? !id.equals(that.id) : that.id != null) &&
                    !(friends != null ? !friends.equals(that.friends) : that.friends != null);
        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            result = 31 * result + (friends != null ? friends.hashCode() : 0);
            return result;
        }
    }

    /**
     * This class represents a serialised {@code multipart/form-data} message. I contains two {@link String} attributes
     * and an {@link java.io.InputStream} to hold a file upload.
     */
    public static class FormDataTestObject {

        private String name;
        private String fileName;
        private InputStream file;


        public FormDataTestObject(String name, String fileName, InputStream file) {

            this.name = name;
            this.fileName = fileName;
            this.file = file;
        }

        /**
         * Return the name attribute sent in the {@code multipart/form-data} message.
         *
         * @return the name value.
         */
        public String getName() {
            return name;
        }

        /**
         * Return the name of the file sent in the {@code multipart/form-data} message.
         *
         * @return the file name value.
         */
        public String getFileName() {
            return fileName;
        }

        /**
         * Return an {@link InputStream} for the contents of the file sent in the {@code multipart/form-data} message.
         *
         * @return the file name value.
         */
        public InputStream getFile() {
            return file;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            FormDataTestObject that = (FormDataTestObject) o;

            String thisFile;
            String thatFile;

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


    /**
     * Default constant values that are used within serialised and deserialised object.
     */
    public static final Long ID = 1L;
    public static final String NAME = "Test User";
    public static final String FRIEND_ONE = "First Friend";
    public static final String FRIEND_TWO = "Second Friend";
    public static final String FRIEND_THREE = "Third Friend";
    public static final Collection<String> FRIENDS = Arrays.asList(FRIEND_ONE, FRIEND_TWO, FRIEND_THREE);

    /**
     * A deserialised test object instance that is used within the serialisation tests.
     */
    public static final TestDeserialisedObject TEST_DESERIALISED_OBJECT = new TestDeserialisedObject(ID, NAME, FRIENDS);

    /**
     * A {@link TestDeserialisedObject} serialised as {@code application/x-www-form-urlencoded}.
     */
    public static final String FORM_URL_ENCODED_SERIALISED_VALUE = "id=" + ID + "&name=" + NAME + "&friend=" +
            FRIEND_ONE + "&friend=" + FRIEND_TWO + "&friend=" + FRIEND_THREE;

    /**
     * A {@link TestDeserialisedObject} serialised as {@code application/json}.
     */
    public static final String JSON_SERIALISED_VALUE = "{" +
            "\"id\":" + ID + "," +
            "\"name\":" + NAME + "," +
            "\"friends\":[" +
            FRIEND_ONE + "," +
            FRIEND_TWO + "," +
            FRIEND_THREE +
            "]" +
            "}";

    /**
     * Multiple constant values used to build different serialised {@code multipart/form-data} combinations.
     */
    public static final String BOUNDERY = "CA90AE";

    public static final String MULTIPART_HEADER = "Content-Type: multipart/form-data; boundary=" + BOUNDERY + "\n\n";
    public static final String MULTIPART_FOOTER = "--" + BOUNDERY + "--";

    public static final String FILE_NAME = "Test User";
    public static final String FILE_CONTENT = "This is the contents of the test file.\n" +
            "We should have a few lines in this really.\n" +
            "Otherwise it would be far too easy.";

    public static final String NAME_PART = "--" + BOUNDERY + "\n" +
            "Content-Disposition: form-data; name=\"name\"\n" +
            "\n" +
            NAME + "\n";

    public static final String FILE_PART = "--" + BOUNDERY + "\n" +
            "Content-Disposition: form-data; name=\"file\"; filename=\"" + FILE_NAME + "\"\n" +
            "Content-Type: text/plain\n" +
            "\n" +
            FILE_CONTENT + "\n";

    public static final String MULTIPART_FORM_DATA_SERIALISED_VALUE = MULTIPART_HEADER + NAME_PART + FILE_PART +
            MULTIPART_FOOTER;
    public static final String MULTIPART_FORM_DATA_SERIALISED_FILE = MULTIPART_HEADER + FILE_PART + MULTIPART_FOOTER;

    public static final FormDataTestObject MULTIPART_FORM_DATA_DESERIALISED_OBJECT = new FormDataTestObject(NAME,
            FILE_NAME, toInputStream(FILE_CONTENT));

    /**
     * A {@link TestDeserialisedObject} serialised as {@code application/xml}.
     */
    public static final String XML_SERIALISED_VALUE = "<test>" +
                "<id>" + ID + "</id>" +
                "<name>" + NAME + "</name>" +
                "<friends>" +
                    "<friend>" + FRIEND_ONE + "</friend>" +
                    "<friend>" + FRIEND_TWO + "</friend>" +
                    "<friend>" + FRIEND_THREE + "</friend>" +
                "</friends>" +
            "</test>";
}
