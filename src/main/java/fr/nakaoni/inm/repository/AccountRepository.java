package fr.nakaoni.inm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.nakaoni.inm.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>
{

}
