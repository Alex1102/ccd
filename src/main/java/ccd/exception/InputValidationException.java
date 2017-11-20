package ccd.exception;

import java.util.Collection;
import java.util.HashSet;

import javax.ejb.ApplicationException;

import org.apache.commons.lang3.StringUtils;

@ApplicationException(rollback = true)
public class InputValidationException extends CcdRuntimerException {

    private static final long serialVersionUID = 1L;

    private final Collection<String> messages;

    public InputValidationException(String message) {
        super(message);
        this.messages = new HashSet<>();
    }

    public InputValidationException(Collection<String> messages) {
        super(StringUtils.join(messages, "\n"));
        this.messages = messages;
    }

    public Collection<String> getMessages() {
        return messages;
    }

}
