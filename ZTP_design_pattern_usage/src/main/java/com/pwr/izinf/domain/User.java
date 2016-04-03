package com.pwr.izinf.domain;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "User")
public class User {


    @Id
    @NotNull
    @Size(max = 64)
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @NotNull
    @Size(max = 64)
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "maxanimal", nullable = false)
    private int maxAnimalNo;


    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Animal> animal;

    public User() {
    }

    public User(final String id, final String password, final int maxAnimalNo) {
        this.id = id;
        this.password = password;
        this.maxAnimalNo = maxAnimalNo;
    }


    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("password", password)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final User other = (User) obj;
        return Objects.equal(this.id, other.id);

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }

    public int getActualAnimalNo() {
        return animal.size();
    }

    public int getMaxAnimalNo() {
        return maxAnimalNo;
    }

    public void setMaxAnimalNo(int maxAnimalNo) {
        this.maxAnimalNo = maxAnimalNo;
    }

    public List<Animal> getAnimal() {
        return animal;
    }

    public void setAnimal(List<Animal> animal) {
        this.animal = animal;
    }

    private User(Builder builder) {
        this.id = id;
        this.password = password;
        this.maxAnimalNo = maxAnimalNo;
    }

    public static class Builder {

        private String id;
        private String password;
        private int maxAnimalNo;
        private List<Animal> animalList;


        public Builder(final String id, final String password, final int maxAnimalNo) {
            this.id = id;
            this.password = password;
            this.maxAnimalNo = maxAnimalNo;
        }

        public User build() {
            return new User(this);
        }

    }

}
