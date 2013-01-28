package http;

import http.header.Header;

import java.util.Collection;

/**
 * Represents an {@code HTTP} response and provides access to all the standard response components.
 *
 * @author Karl Bennett
 */
public class Response<T> extends Message<T> {

    /**
     * Create a {@code Response} with the supplied HTTP status code.
     *
     * @param status the status code for the response.
     */
    public Response(int status) {
    }

    /**
     * Create a {@code Response} with the supplied HTTP status code and body.
     *
     * @param status the status code for the response.
     * @param body   the body of te response.
     */
    public Response(int status, T body) {
    }

    /**
     * Create a {@code Response} with the supplied HTTP status code, headers, and body.
     *
     * @param status  the status code for the response.
     * @param headers the headers for the response.
     * @param body    the body of te response.
     */
    public Response(int status, Collection<Header> headers, T body) {
    }

    /**
     * Create a {@code Response} with the supplied HTTP status code, headers, and body.
     *
     * @param status  the status code for the response.
     * @param headers the headers for the response.
     * @param cookies the cookies for the response.
     * @param body    the body of te response.
     */
    public Response(int status, Collection<Header> headers, Collection<Cookie> cookies, T body) {
    }

    /**
     * @return the status code for the response.
     */
    public int getStatus() {

        return 0;
    }

    /**
     * Set the status code for the response.
     *
     * @param status the status code for the response.
     */
    public void setStatus(int status) {

    }
}
