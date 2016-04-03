package com.pwr.izinf.domain;

import com.google.common.base.MoreObjects;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Sandra on 2016-02-28.
 */

@Entity
@Table(name = "Animal")
public class Animal {


    @Id
    @NotNull
    @Size(max = 20)
    @Column(name = "id", nullable = false, updatable = false)
    private String id;


    @NotNull
    @Size(max = 64)
    @Column(name = "nick", nullable = false)
    private String nick;

    @Size(max = 2)
    @Column(name = "age")
    private String age;

    @NotNull
    @Size(max = 64)
    @Column(name = "spieces", nullable = false)
    private String spieces;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User owner;

    public Animal() {
    }

//    public Animal(String id, String nick, String spieces) {
//        this.id = id;
//        this.nick = nick;
//        this.spieces = spieces;
//    }
//
//    public Animal(String id, String nick, String spieces, User owner) {
//        this.id = id;
//        this.nick = nick;
//        this.spieces = spieces;
//        this.owner = owner;
//    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpieces() {
        return spieces;
    }

    public void setSpieces(String spieces) {
        this.spieces = spieces;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("nick", nick)
                .add("spieces", spieces)
                .add("age", animalAge())
                .add("owner", ownerData())
                .toString();
    }

    private String ownerData() {
        return owner == null ? "Without owner" : owner.toString();
    }

    private String animalAge() {
        return age == null ? "No age specified" : age;
    }

    private Animal(Builder builder) {
        this.id = builder.id;
        this.nick = builder.nick;
        this.spieces = builder.spieces;
        this.age = builder.age;
        this.owner = builder.owner;
    }

    public static class Builder {

        private String id;
        private String nick;
        private String spieces;
        private String age;
        private User owner;


        public Builder(String id, String nick, String spieces, String age) {
            this.id = id;
            this.nick = nick;
            this.spieces = spieces;
            this.age = age;
        }

        public Builder withOwner(User owner) {
            this.owner = owner;
            return this;
        }

        public Animal build() {
            return new Animal(this);
        }

    }

}
