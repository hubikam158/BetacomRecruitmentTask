package com.betacom.rekrutacja.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "owner",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Item> items;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
