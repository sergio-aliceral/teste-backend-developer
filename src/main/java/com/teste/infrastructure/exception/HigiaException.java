package com.teste.infrastructure.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public abstract class HigiaException extends RuntimeException {

    private static final long serialVersionUID = 4220212442387105196L;

    protected HigiaException() {
        this(null, null);
    }

    protected HigiaException(final String message) {
        super(message);
    }

    protected HigiaException(final Throwable cause) {
        super(cause);
    }

    protected HigiaException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public MessageDto buildResponse() {
        final MessageDto responseDefault = new MessageDto();        
        responseDefault.setMessage(getMensagem());
        responseDefault.setDetails(this.getLocalizedMessage());
        responseDefault.setErrors(getAdditionalMessages());
        return responseDefault;
    }

    public abstract HttpStatus getStatusCode();

    public List<MessageDto> getAdditionalMessages() {
        return new ArrayList<>();
    }

    protected Integer getCodigo() {
        return getStatusCode().value();
    }

    protected String getMensagem() {
        return getMessage();
    }


}
