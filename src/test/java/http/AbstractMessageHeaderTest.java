package http;

/**
 * @author Karl Bennett
 */
public abstract class AbstractMessageHeaderTest<M> extends AbstractMessageAttributeTest<M, Header> {

    public AbstractMessageHeaderTest(MessageExecutor<M, Header> messageExecutor) {

        super(new PropertyExecutor<Header>() {

            @Override
            public <T> Header newProperty(String name, T value) {

                return new Header<T>(name, value);
            }

            @Override
            public String getName(Header property) {

                return property.getName();
            }

            @Override
            @SuppressWarnings("unchecked")
            public <T> T getValue(Header property) {

                return (T) property.getValue();
            }

        }, messageExecutor);
    }
}
