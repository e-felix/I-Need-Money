package fr.nakaoni.inm.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "transaction")
public class Transaction implements Resource {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(targetEntity = Category.class)
    private Category category;

    /**
     * Refactor by creating an Entity
     */
    private String payee;

    @ManyToOne(targetEntity = Account.class)
    private Account account;

    private String comment;

    private Boolean cleared;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private TransactionType type;

    /**
     * Refactor to Value Object Amount{value:int,currency:string}
     */
    private int amount;

    public Transaction() {
        this.cleared = false;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public Transaction(
            Long id,
            Category category,
            String payee,
            Account account,
            String comment,
            Boolean cleared,
            TransactionType type,
            int amount
    ) {
        this.id = id;
        this.category = category;
        this.payee = payee;
        this.account = account;
        this.comment = comment;
        this.cleared = cleared;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.type = type;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getCleared() {
        return cleared;
    }

    public void setCleared(Boolean cleared) {
        this.cleared = cleared;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return amount == that.amount && Objects.equals(id, that.id) && Objects.equals(category, that.category) && Objects.equals(payee, that.payee) && Objects.equals(account, that.account) && Objects.equals(comment, that.comment) && Objects.equals(cleared, that.cleared) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, payee, account, comment, cleared, createdAt, updatedAt, type, amount);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", category=" + category +
                ", payee='" + payee + '\'' +
                ", account=" + account +
                ", comment='" + comment + '\'' +
                ", cleared=" + cleared +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", type=" + type +
                ", amount=" + amount +
                '}';
    }
}
