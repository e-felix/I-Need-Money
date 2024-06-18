package fr.nakaoni.inm.exception;

public class BankNotFoundException extends RuntimeException {

    public BankNotFoundException(Long id) {
        super("Could not find bank " + id);
    }

}
