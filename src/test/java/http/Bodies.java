package http;

import java.io.InputStream;

import static org.apache.commons.io.IOUtils.*;

/**
 * A utility class containing constant body content values that can be used within tests.
 *
 * @author Karl Bennett
 */
public final class Bodies {

    private Bodies() {
    }

    /**
     * Simple holder class to stop the {@link Object} body from picking up the {@link String} type.
     */
    private static class ObjectBodyHolder {

        private String body;

        private ObjectBodyHolder(String body) {

            this.body = body;
        }

        @Override
        public String toString() {

            return body;
        }
    }

    public static final String TEST_STRING_BODY = "test string body";

    public static final Object TEST_OBJECT_BODY = new ObjectBodyHolder("test object body");

    public static final String TEST_INPUT_STREAM_BODY_STRING = "test input stream body";

    public static final InputStream TEST_INPUT_STREAM_BODY = toInputStream(TEST_INPUT_STREAM_BODY_STRING);
}
