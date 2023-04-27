package com.ptit.webserviceelectronicshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "users")
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String full_name;
    @Column
    @JsonIgnore
    private String password;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String address;
    @Column
    private String role;
    @Column
    private String avatar;

    public User() {

    }

}
