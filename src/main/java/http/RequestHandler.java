package http;

import java.io.InputStream;
import java.net.URL;
import java.util.Collection;

import static http.Client.Response;

/**
 * The handler interface that must be implemented to provide a working {@link Client} backend.
 *
 * @author Karl Bennett
 */
interface RequestHandler {

    /**
     * This is called by every {@link Client} request method and must be implemented to provide that actual backend
     * logic that will actually carry out the HTTP request.
     *
     * @param method     the HTTP method for the request e.g. "GET", "POST", "PUT", "DELETE"...
     * @param url        the url for the request.
     * @param headers    any headers contained within the request.
     * @param cookies    any cookies contained within the request.
     * @param parameters any parameters contained within the request.
     * @param body       an input stream to any content contained within the body of the request. This may be null.
     * @return a {@link Response} object containing all the data contain in the HTTP response.
     */
    public Response<InputStream> handleRequest(String method, URL url, Collection<Header> headers,
                                               Collection<Cookie> cookies, Collection<Parameter> parameters,
                                               InputStream body);
}
