package http.util;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static http.util.IO.*;
import static org.apache.commons.io.IOUtils.*;
import static org.junit.Assert.*;

/**
 * @author Karl Bennett
 */
public class IOTest {

    private static final String TEST_STRING = "test input stream string";

    private static final InputStream TEST_INPUT_STREAM = toInputStream(TEST_STRING);

    @Test
    public void testReadAll() throws Exception {

        assertEquals("readAll should read in the correct string.", TEST_STRING, readAll(TEST_INPUT_STREAM));
    }

    @Test
    public void testReadAllWithEmptyInputStream() throws Exception {

        assertEquals("readAll should read in nothing.", "", readAll(toInputStream("")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadAllWithNullInputStream() throws Exception {

        readAll(null);
    }
}
