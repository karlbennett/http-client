package http;

import static http.Client.Response;

/**
 * This interface is used to allow the switching of request methods within the same peace of code. It used to allow code
 * reuse within the HTTP request methods tests which are all very similar.
 *
 * @param <T> the type of input that will be used for the request method, this will usually be the {@link String} or
 * {@link http.Client.Request} type.
 *
 * @author Karl Bennett
 */
public interface RequestExecutor<T> {

    /**
     * @return the method string for the contained request e.g. "GET", "POST", "PUT", "DELETE"...
     */
    public String getMethod();

    /**
     * @return whether a static or instance method is being called.
     */
    public String getMethodType();

    /**
     * Execute the wrapped request method method. This could be a static or instance method e.g.
     * {@link Client#GET(String)}, {@link Client#POST(String)}, {@link Client#PUT(String)}, or
     * {@link Client#get(String)}, {@link Client#post(String)}, {@link Client#put(String)}...
     *
     * @param input the input for the request method, it's type is set generically for the class.
     * @return the response object returned from the request method.
     */
    public Response execute(T input);
}
