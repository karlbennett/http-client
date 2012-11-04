package http;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;

/**
 * Represents an {@code HTTP} request and can be populated with all the standard request components.
 *
 * @author Karl Bennett
 */
public class Request<T> extends Message<T> {

    /**
     * Generate a new {@code java.net.URL} instance from a {@code java.lang.String} without throwing a checked
     * exception.
     *
     * @param url the url string to use to create the new {@code java.net.URL} instance.
     * @return a new {@code java.net.URL} instance.
     * @throws URLException if an invalid url string is given.
     */
    private static URL quietUrl(String url) {

        try {

            return new URI(url).toURL();

        } catch (MalformedURLException e) {

            throw new URLException(e);

        } catch (URISyntaxException e) {

            throw new URLException(e);
        }
    }


    /**
     * Create a new {@code Request} that will be sent to the {@code HTTP} server at the supplied {@link URL}.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     */
    public Request(String url) {

        this(quietUrl(url));
    }

    /**
     * Create a new {@code Request} that will be sent to the {@code HTTP} server at the supplied {@link URL}.
     *
     * @param url a {@code java.lang.String} containing the {@code java.net.URL} for the {@code HTTP} server.
     */
    public Request(URL url) {
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
     *
     * Care must be taken with this method because it will implicitly cast the generic type of the {@code Parameter} so
     * can produce {@link ClassCastException} exceptions at runtime.
     *
     * <code>
     *     request.addParameter(new Parameter&lt;String&gt;("number", "one"));
     *     Parameter&lt;Integer&gt; parameter = request.getParameter("number"); // This will not produce an unchecked warning.
     *     int number = parameter.getValue(); // This will compile and fail at runtime with a ClassCastException.
     * </code>
     *
     * @param name the name of the parameter to retrieve.
     * @param <T> the type of the parameters value.
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
     * @param name the name of the new parameter.
     * @param value the value for the new parameter.
     * @param <T> the type of the parameters value.
     */
    public <T> void addParameter(String name, T value) {

        addParameter(new Parameter<T>(name, value));
    }

    /**
     * Add a {@link Parameter} to the {@code Message} appending it to any added previously. If a parameter with the a
     * matching name already exists then the new parameters value will be added to the existing parameters values.
     *
     * @param parameter the new parameter to add to the message.
     * @param <T> the type of the parameters value.
     */
    public <T> void addParameter(Parameter<T> parameter) {

    }
}
