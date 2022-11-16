package com.infosys.exception;

/**
 * @author 'Seemant Shukla' on '15/11/2022'
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
