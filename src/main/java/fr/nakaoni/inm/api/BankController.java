package fr.nakaoni.inm.api;

import fr.nakaoni.inm.exception.EntityNotFoundException;
import org.springframework.web.bind.annotation.RestController;

import fr.nakaoni.inm.assembler.BankModelAssembler;
import fr.nakaoni.inm.entity.Bank;
import fr.nakaoni.inm.exception.BankNotFoundException;
import fr.nakaoni.inm.repository.BankRepository;

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
public class BankController {
    private final BankRepository bankRepository;
    private final BankModelAssembler bankModelAssembler;

    public BankController(BankRepository bankRepository, BankModelAssembler bankModelAssembler) {
        this.bankRepository = bankRepository;
        this.bankModelAssembler = bankModelAssembler;
    }

    @GetMapping("/banks")
    public CollectionModel<EntityModel<Bank>> all() {
        List<EntityModel<Bank>> banks = this.bankRepository
                .findAll()
                .stream()
                .map(bankModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(banks, linkTo(methodOn(BankController.class).all()).withSelfRel());
    }

    @GetMapping("/banks/{id}")
    public EntityModel<Bank> get(@PathVariable Long id) {
        Bank bank = this.bankRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Bank.class.getSimpleName()));

        return bankModelAssembler.toModel(bank);
    }

    @PostMapping("/banks")
    public EntityModel<Bank> post(@RequestBody Bank newBank) {
        Bank bank = this.bankRepository
                .save(newBank);

        return bankModelAssembler.toModel(bank);
    }

    @PutMapping("/banks/{id}")
    public EntityModel<Bank> post(@RequestBody Bank newBank, @PathVariable Long id) {

        Bank savedBank = this.bankRepository
                .findById(id)
                .map(bank -> {
                    bank.setName(newBank.getName());
                    return bankRepository.save(bank);
                }).orElseGet(() -> {
                    return bankRepository.save(newBank);
                });

        return bankModelAssembler.toModel(savedBank);
    }

    @DeleteMapping("/banks/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        bankRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
