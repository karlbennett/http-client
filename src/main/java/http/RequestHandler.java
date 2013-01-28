package http;

import java.io.InputStream;

/**
 * The handler interface that must be implemented to provide a working {@link Client} backend.
 *
 * @author Karl Bennett
 */
interface RequestHandler {

    /**
     * This is called by every {@link Client} request method and must be implemented to provide that actual backend
     * logic that will carry out the HTTP request.
     *
     * @param method  the HTTP method for the request e.g. "GET", "POST", "PUT", "DELETE"...
     * @param request the request object that contains the request details.
     * @return a {@link Response} object containing all the data contain in the HTTP response.
     */
    public Response<InputStream> handleRequest(String method, Request<InputStream> request);
}
