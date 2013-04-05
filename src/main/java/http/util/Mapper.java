package http.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static http.util.Checks.isNotNull;
import static http.util.Checks.isNull;

/**
 * A class that provides the ability to define logic that will be run across a collection to produce a new collection
 * containing new elements.
 *
 * @author Karl Bennett
 */
public abstract class Mapper<T, R> {

    private final Collection<T> elements;
    private List<R> results;

    /**
     * Create a new {@code Mapper} for the supplied collection.
     *
     * @param elements the collection to iterate over.
     */
    public Mapper(Collection<T> elements) {

        this.elements = elements;
    }

    /**
     * Create a new {@code Mapper} for the supplied array.
     *
     * @param elements the array to iterate over.
     */
    public Mapper(T[] elements) {

        this(null == elements ? null : Arrays.asList(elements));
    }


    /**
     * Execute the mapping operation and return the resultant {@link List}.
     *
     * @return the resulting list.
     */
    public List<R> results() {

        if (isNull(elements)) {

            return null;
        }

        return results(elements.size());
    }

    /**
     * Execute the mapping operation and return the resultant {@link List} that will have the supplied initial size.
     *
     * @param size the size to allocate for the result list.
     * @return the resulting list.
     */
    public List<R> results(int size) {

        // If the elements collection is null then quit early.
        if (isNull(elements)) {

            return null;
        }

        results = new ArrayList<R>(size);

        R result;

        for (T element : elements) {

            result = next(element);

            if (null != result) results.add(result);
        }

        return results;
    }

    /**
     * Add all the elements within the supplied {@link Collection} to the {@code results} list.
     *
     * @param elements the elements to add.
     */
    protected void addAll(Collection<R> elements) {

        if (isNotNull(elements)) this.results.addAll(elements);
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
