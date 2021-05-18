package org.contextmapper.archunit.cml;

public class BoundedContextDoesNotExistException extends RuntimeException {

    public BoundedContextDoesNotExistException(final String name) {
        super("A Bounded Context with the name '" + name + "' does not exist in the CML model");
    }

}
