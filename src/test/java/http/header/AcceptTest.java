package http.header;

import org.junit.Test;

import static http.util.MimeTypes.*;
import static http.header.Accept.ACCEPT;
import static org.junit.Assert.*;

/**
 * @author Karl Bennett
 */
public class AcceptTest {

    @Test
    public void testCreateAccept() throws Exception {

        Accept accept = new Accept(APPLICATION, JSON);

        assertEquals("the accept name should be correct.", ACCEPT, accept.getName());
    }
}
