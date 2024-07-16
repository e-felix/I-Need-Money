package fr.nakaoni.inm.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id, String className) {
        super("Could not find " + className + " " + id);
    }

}
