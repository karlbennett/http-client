package http;

import static http.Client.Response;

/**
 * This interface is used to allow the switching of request methods within the same peace of code. It's used to allow
 * code reuse within the HTTP request methods tests which are all very similar.
 *
 * @param <T> the type of input that will be used for the request method, this will usually be the {@link String},
 * {@link java.net.URL}, or {@link http.Client.Request} type.
 *
 * @author Karl Bennett
 */
public interface RequestExecutor<T> {

    /**
     * Denotes the type of a request method, that is static method like {@link Client#GET(String)},
     * {@link Client#POST(String)}, {@link Client#PUT(String)}, or an instance method like {@link Client#get(String)},
     * {@link Client#post(String)}, {@link Client#put(String)}.
     */
    public static enum METHOD_TYPE {
        STATIC,
        INSTANCE
    }

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
