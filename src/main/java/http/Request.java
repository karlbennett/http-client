package http;

import http.header.Header;
import http.parameter.Parameter;

import java.net.URL;
import java.util.Collection;
import java.util.Collections;

import static http.util.URIs.quietUrl;

/**
 * Represents an {@code HTTP} request and can be populated with all the standard request components.
 *
 * @author Karl Bennett
 */
public class Request<T> extends Message<T> {

    /**
     * Create a new {@code Request} that will be sent to the {@code HTTP} server at the supplied {@link URL}.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     */
    public Request(String url) {

        this(quietUrl(url));
    }

    /**
     * Create a new {@code Request} that will be sent to the {@code HTTP} server at the supplied {@link URL}. It
     * will aso contain parameters supplied.
     *
     * @param url        a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @param parameters any parameters that should be sent in the HTTP request.
     */
    public Request(String url, Collection<Parameter> parameters) {

        this(quietUrl(url), parameters);
    }

    /**
     * Create a new {@code Request} that will be sent to the {@code HTTP} server at the supplied {@link URL}. It
     * will aso contain the headers and parameters supplied.
     *
     * @param url        a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @param headers    any headers that should be sent in the HTTP request.
     * @param parameters any parameters that should be sent in the HTTP request.
     */
    public Request(String url, Collection<Header> headers, Collection<Parameter> parameters) {

        this(quietUrl(url), headers, parameters);
    }

    /**
     * Create a new {@code Request} that will be sent to the {@code HTTP} server at the supplied {@link URL}. It
     * will aso contain the headers, parameters, and cookies supplied.
     *
     * @param url        a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @param headers    any headers that should be sent in the HTTP request.
     * @param cookies    any cookies that should be sent in the HTTP request.
     * @param parameters any parameters that should be sent in the HTTP request.
     */
    public Request(String url, Collection<Header> headers, Collection<Cookie> cookies, Collection<Parameter> parameters) {

        this(quietUrl(url), headers, cookies, parameters);
    }

    /**
     * Create a new {@code Request} that will be sent to the {@code HTTP} server at the supplied {@link URL}.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     */
    public Request(URL url) {

        this(url, Collections.<Parameter>emptySet());
    }


    /**
     * Create a new {@code Request} that will be sent to the {@code HTTP} server at the supplied {@link URL}. It
     * will aso contain parameters supplied.
     *
     * @param url        a {@code java.net.URL} representing the URL for the {@code HTTP} server.
     * @param parameters any parameters that should be sent in the HTTP request.
     */
    public Request(URL url, Collection<Parameter> parameters) {

        this(url, Collections.<Header>emptySet(), parameters);
    }

    /**
     * Create a new {@code Request} that will be sent to the {@code HTTP} server at the supplied {@link URL}. It
     * will aso contain the headers and parameters supplied.
     *
     * @param url        a {@code java.net.URL} representing the URL for the {@code HTTP} server.
     * @param headers    any headers that should be sent in the HTTP request.
     * @param parameters any parameters that should be sent in the HTTP request.
     */
    public Request(URL url, Collection<Header> headers, Collection<Parameter> parameters) {

        this(url, headers, Collections.<Cookie>emptySet(), parameters);
    }

    /**
     * Create a new {@code Request} that will be sent to the {@code HTTP} server at the supplied {@link URL}. It
     * will aso contain the headers, parameters, and cookies supplied.
     *
     * @param url        a {@code java.net.URL} representing the URL for the {@code HTTP} server.
     * @param headers    any headers that should be sent in the HTTP request.
     * @param cookies    any cookies that should be sent in the HTTP request.
     * @param parameters any parameters that should be sent in the HTTP request.
     */
    public Request(URL url, Collection<Header> headers, Collection<Cookie> cookies, Collection<Parameter> parameters) {
    }


    /**
     * @return the url for this request.
     */
    public URL getUrl() {

        return null;
    }

    /**
     * Get all the parameters set for the current {@code Message}.
     *
     * @return the message parameters.
     */
    public Collection<Parameter> getParameters() {

        return null;
    }

    /**
     * Get the {@link Parameter} with the supplied name. If the parameter does not exist this method will return null.
     * <p/>
     * Care must be taken with this method because it will implicitly cast the generic type of the {@code Parameter} so
     * can produce {@link ClassCastException} exceptions at runtime.
     * <p/>
     * <code>
     * request.addParameter(new Parameter&lt;String&gt;("number", "one"));
     * Parameter&lt;Integer&gt; parameter = request.getParameter("number"); // This will not produce an unchecked warning.
     * int number = parameter.getValue(); // This will compile and fail at runtime with a ClassCastException.
     * </code>
     *
     * @param name the name of the parameter to retrieve.
     * @param <T>  the type of the parameters value.
     * @return the requested parameter if it exists otherwise null.
     */
    public <T> Parameter<T> getParameter(String name) {

        return null;
    }

    /**
     * Set the parameters for this {@code Message}, overwriting any that might have previously been set.
     *
     * @param parameters the new parameters for the message.
     */
    public void setParameters(Collection<Parameter> parameters) {

    }

    /**
     * Add a parameter to the {@code Message} made up of the supplied name and value. If a parameter with the supplied
     * name already exists then the supplied value will be added to the existing parameters values.
     *
     * @param name  the name of the new parameter.
     * @param value the value for the new parameter.
     * @param <T>   the type of the parameters value.
     */
    public <T> void addParameter(String name, T value) {

        addParameter(new Parameter<T>(name, value));
    }

    /**
     * Add a {@link Parameter} to the {@code Message} appending it to any added previously. If a parameter with the a
     * matching name already exists then the new parameters value will be added to the existing parameters values.
     *
     * @param parameter the new parameter to add to the message.
     * @param <T>       the type of the parameters value.
     */
    public <T> void addParameter(Parameter<T> parameter) {

    }
}
