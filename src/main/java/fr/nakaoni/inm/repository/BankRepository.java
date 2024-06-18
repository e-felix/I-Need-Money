package fr.nakaoni.inm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.nakaoni.inm.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Long>
{

}
