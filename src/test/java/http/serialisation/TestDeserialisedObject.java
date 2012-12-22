package http.serialisation;

import java.util.Arrays;
import java.util.Collection;

/**
 * A class that can be used as a container to deserialise test data into. It also contains constant test values.
 *
 * @author Karl Bennett
 */
public class TestDeserialisedObject {

    public static final Long ID = 1L;
    public static final String NAME = "Test User";
    public static final String FRIEND_ONE = "First Friend";
    public static final String FRIEND_TWO = "Second Friend";
    public static final String FRIEND_THREE = "Third Friend";
    public static final Collection<String> FRIENDS = Arrays.asList(FRIEND_ONE, FRIEND_TWO, FRIEND_THREE);

    public static final TestDeserialisedObject TEST_DESERIALISED_OBJECT = new TestDeserialisedObject(ID, NAME, FRIENDS);


    private Long id;
    private String name;
    private Collection<String> friends;


    public TestDeserialisedObject(Long id, String name, Collection<String> friends) {

        this.id = id;
        this.name = name;
        this.friends = friends;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<String> getFriends() {
        return friends;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestDeserialisedObject that = (TestDeserialisedObject) o;

        return !(name != null ? !name.equals(that.name) : that.name != null) &&
                !(id != null ? !id.equals(that.id) : that.id != null) &&
                !(friends != null ? !friends.equals(that.friends) : that.friends != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (friends != null ? friends.hashCode() : 0);
        return result;
    }
}
