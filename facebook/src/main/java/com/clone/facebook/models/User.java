package com.clone.facebook.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class User extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String familyName;

    @Column(nullable = false)
    private String givenName;

    @Column(nullable = false, unique = true)
    private String mail;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String year;

    @Column(nullable = false)
    private String month;

    @Column(nullable = false)
    private String day;

    @Column(nullable = true)
    private String imageUrl;


    public User(String familyName, String givenName, String mail, String password, String year, String month, String day) {
        this.familyName = familyName;
        this.givenName = givenName;
        this.mail = mail;
        this.password = password;
        this.year = year;
        this.month = month;
        this.day = day;
    }

}
