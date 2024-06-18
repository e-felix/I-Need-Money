package fr.nakaoni.inm.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import fr.nakaoni.inm.api.AccountController;
import fr.nakaoni.inm.entity.Account;

@Component
public class AccountModelAssembler implements RepresentationModelAssembler<Account, EntityModel<Account>> {

    @Override
    public EntityModel<Account> toModel(Account bank) {
        return EntityModel.of(
            bank,
            linkTo(methodOn(AccountController.class).get(bank.getId())).withSelfRel(),
            linkTo(methodOn(AccountController.class).all()).withRel("banks")
        );
    }

}
