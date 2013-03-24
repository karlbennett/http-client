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

        Accept accept = new Accept(APPLICATION_JSON);

        assertAccept(accept);
    }

    @Test
    public void testCreateAcceptWithPrimaryAndSubStrings() throws Exception {

        Accept accept = new Accept(APPLICATION, JSON);

        assertAccept(accept);
    }

    @Test
    public void testCreateAcceptWithCopyConstructor() throws Exception {

        Accept accept = new Accept(APPLICATION_JSON);

        Accept acceptCopy = new Accept(accept);

        assertEquals("the copy accept should equal the original.", accept, acceptCopy);

        assertAccept(acceptCopy);
    }

    @Test
    public void testCreateAcceptWithConversion() throws Exception {

        Header header = new Header<Object>(ACCEPT, APPLICATION + '/' + JSON);

        Accept accept = new Accept(header);

        assertAccept(accept);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAcceptWithInvalidHeaderType() throws Exception {

        new Accept(new Header<Object>("Test", new Object()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAcceptWithInvalidHeaderValue() throws Exception {

        new Accept(new Header<Object>("Test", new Object()));
    }


    private static void assertAccept(Accept accept) {

        assertEquals("the accept name should be correct.", ACCEPT, accept.getName());
        assertEquals("the accept value should be correct.", APPLICATION_JSON, accept.getValue());
    }
}
