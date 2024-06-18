package fr.nakaoni.inm.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import fr.nakaoni.inm.api.BankController;
import fr.nakaoni.inm.entity.Bank;

@Component
public class BankModelAssembler implements RepresentationModelAssembler<Bank, EntityModel<Bank>> {

    @Override
    public EntityModel<Bank> toModel(Bank bank) {
        return EntityModel.of(
            bank,
            linkTo(methodOn(BankController.class).get(bank.getId())).withSelfRel(),
            linkTo(methodOn(BankController.class).all()).withRel("banks")
        );
    }

}
