package fr.nakaoni.inm.api;

import org.springframework.web.bind.annotation.RestController;

import fr.nakaoni.inm.assembler.AccountModelAssembler;
import fr.nakaoni.inm.entity.Account;
import fr.nakaoni.inm.exception.AccountNotFoundException;
import fr.nakaoni.inm.repository.AccountRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class AccountController {
    private final AccountRepository accountRepository;
    private final AccountModelAssembler accountModelAssembler;

    public AccountController(AccountRepository accountRepository, AccountModelAssembler accountModelAssembler) {
        this.accountRepository = accountRepository;
        this.accountModelAssembler = accountModelAssembler;
    }

    @GetMapping("/accounts")
    public CollectionModel<EntityModel<Account>> all() {
        List<EntityModel<Account>> accounts = this.accountRepository
                .findAll()
                .stream()
                .map(accountModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(accounts, linkTo(methodOn(AccountController.class).all()).withSelfRel());
    }

    @GetMapping("/accounts/{id}")
    public EntityModel<Account> get(@PathVariable Long id) {
        Account account = this.accountRepository
                .findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        return accountModelAssembler.toModel(account);
    }

    @PostMapping("/accounts")
    public EntityModel<Account> post(@RequestBody Account newAccount) {
        Account account = this.accountRepository
                .save(newAccount);

        return accountModelAssembler.toModel(account);
    }

    @PutMapping("/accounts/{id}")
    public EntityModel<Account> post(@RequestBody Account newAccount, @PathVariable Long id) {

        Account savedAccount = this.accountRepository
                .findById(id)
                .map(account -> {
                    account.setName(newAccount.getName());
                    return accountRepository.save(account);
                }).orElseGet(() -> {
                    return accountRepository.save(newAccount);
                });

        return accountModelAssembler.toModel(savedAccount);
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        accountRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
