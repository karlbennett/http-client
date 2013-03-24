package http.util;

import java.util.*;

import static http.util.Checks.isNotNull;

/**
 * A class that provides forEach functionality that can be used safely on a {@code null} collection.
 *
 * @author Karl Bennett
 */
public abstract class NullSafeForEach<T, R> {

    private final Set<R> results;

    /**
     * Create a new {@code NullSafeForEach} for the supplied collection.
     *
     * @param elements the collection to iterate over.
     */
    public NullSafeForEach(Collection<T> elements) {

        if (isNotNull(elements)) {

            results = new HashSet<R>(elements.size());

            R result;

            for (T element : elements) {

                result = next(element);

                if (null != result) results.add(result);
            }

        } else {

            results = null;
        }
    }

    /**
     * Create a new {@code NullSafeForEach} for the supplied array.
     *
     * @param elements the array to iterate over.
     */
    public NullSafeForEach(T[] elements) {

        this(null == elements ? null : Arrays.asList(elements));
    }


    /**
     * Get the {@link List} created from the result of the for each.
     *
     * @return the resulting list.
     */
    public Set<R> results() {

        return results;
    }

    /**
     * Implement this method to get access to each of the collections elements. It will be called once for each element.
     *
     * @param element the next element in the collection.
     * @return an element that will be added to the {@link #results} list, otherwise return null to add nothing to the
     *         results list.
     */
    protected abstract R next(T element);
}
