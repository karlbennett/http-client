package http;

import java.io.InputStream;
import java.util.Collection;

/**
 * @author Karl Bennett
 */
interface RequestHandler {

    public Response handleRequest(String method, Collection<Header> headers, Collection<Parameter> parameters,
                                  Collection<Cookie> cookies, InputStream body);
}
