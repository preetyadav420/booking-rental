package com.preet.bookingrental.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private String username;

    @NotNull
    @JsonIgnore
    private String password;

    @NotNull
    private String email;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Listing> listings = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Booking> bookings = new ArrayList<>();

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;
}
