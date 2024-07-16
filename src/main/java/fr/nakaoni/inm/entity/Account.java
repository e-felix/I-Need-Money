package fr.nakaoni.inm.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "bank_account")
public class Account implements Resource {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private AccountType type;

    private Long balance;

    @ManyToOne(targetEntity = Bank.class)
    private Bank bank;

    @OneToMany(targetEntity = Category.class)
    private Set<Category> categories;

    public Account() {
        this.type = AccountType.CHECKING;
        this.categories = new HashSet<>();
    }

    public Account(String name, AccountType type, Long balance, Bank bank, Set<Category> categories) {
        this.name = name;
        this.type = type;
        this.balance = balance;
        this.bank = bank;
        this.categories = categories;
    }

    enum Type {
        CHECKING,
        SAVINGS
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Set<Category> getCategories() {
        return this.categories;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void removeCategory(Category category) {
        this.categories.remove(category);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Objects.equals(id, account.id) && Objects.equals(name, account.name) && type == account.type && Objects.equals(balance, account.balance) && Objects.equals(bank, account.bank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, balance, bank);
    }

    @Override
    public String toString() {
        return "Bank { id: " + getId() + ", name: " + getName() + " }";
    }
}
