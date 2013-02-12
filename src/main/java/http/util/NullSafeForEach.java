package http.util;

import static http.util.Checks.isNotNull;

/**
 * A class that provides forEach functionality that can be used safely on a {@code null} collection.
 *
 * @author Karl Bennett
 */
public abstract class NullSafeForEach<T> {

    /**
     * Create a new {@code NullSafeForEach} for the supplied iterable collection.
     *
     * @param iterable the collection to iterate over.
     */
    public NullSafeForEach(Iterable<T> iterable) {

        if (isNotNull(iterable)) for (T element : iterable) next(element);
    }

    /**
     * Create a new {@code NullSafeForEach} for the supplied array.
     *
     * @param elements the array to iterate over.
     */
    public NullSafeForEach(T[] elements) {

        if (isNotNull(elements)) for (T element : elements) next(element);
    }

    /**
     * Implement this method to get access to each of the collections elements. It will be called once for each element.
     *
     * @param element the next element in the collection.
     */
    protected abstract void next(T element);
}
