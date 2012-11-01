package http;

import java.io.OutputStream;

/**
 * Represents an {@code HTTP} response and provides access to all the standard response components.
 *
 * @author Karl Bennett
 */
public class Response extends Message<OutputStream> {

    public int getStatus() {

        return 0;
    }

    public void setStatus(int status) {

    }
}
