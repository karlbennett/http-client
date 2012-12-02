package http.conversion;

import http.header.FormDataContentType;

import java.io.File;
import java.io.InputStream;

/**
 * @author Karl Bennett
 */
public class FormDataContentTypeOutputConverter extends InputStreamContentTypeOutputConverter<FormDataContentType> {

    protected FormDataContentTypeOutputConverter() {
        super(FormDataContentType.class);
    }

    @Override
    public <T> T convert(Class<T> type, InputStream input) {

        return null;
    }

    public InputStream convertToInputStream(InputStream input) {

        return null;
    }

    public byte[] convertToByteArray(InputStream input) {

        return null;
    }

    public String convertToString(InputStream input) {

        return null;
    }

    public File convertToFile(InputStream input) {

        return null;
    }
}
