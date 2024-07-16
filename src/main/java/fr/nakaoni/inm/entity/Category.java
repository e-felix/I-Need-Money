package fr.nakaoni.inm.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Category implements Resource {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(targetEntity = Account.class)
    private Account account;

    @ManyToOne(targetEntity = Category.class)
    private Category parentCategory;

    @OneToMany(targetEntity = Category.class)
    private Set<Category> subCategories;

    public Category() {
        this.subCategories = new HashSet<>();
    }

    public Category(Long id, String name, Account account, Category parentCategory, Set<Category> subCategories) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.parentCategory = parentCategory;
        this.subCategories = subCategories;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set<Category> getSubCategories() {
        return subCategories;
    }

    public void addSubCategory(Category subCategory) {
        this.subCategories.add(subCategory);
    }

    public void removeSubCategory(Category subCategory) {
        this.subCategories.remove(subCategory);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name) && Objects.equals(account, category.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, account);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", account=" + this.getAccount() +
                '}';
    }

}
