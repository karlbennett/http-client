package http;

import java.io.InputStream;
import java.util.Collection;

import static http.Client.Response;

/**
 * @author Karl Bennett
 */
interface RequestHandler {

    public Response handleRequest(String method, Collection<Header> headers, Collection<Parameter> parameters,
                                  Collection<Cookie> cookies, InputStream body);
}
