package fr.nakaoni.inm.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(Long id) {
        super("Could not find account " + id);
    }

}
