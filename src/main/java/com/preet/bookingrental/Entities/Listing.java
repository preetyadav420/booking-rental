package com.preet.bookingrental.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="listings")
@Data
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;


    @ManyToOne
    @JoinColumn(name = "vendor_id")
    @NotNull
    private User vendor;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Booking> bookings = new ArrayList<>();

    @NotNull
    private String title;

    private String description;

    @NotNull
    @Column(name = "price_per_day")
    private BigDecimal pricePerDay;

    @Column(name="created_at", insertable = false, updatable = false)
    private LocalDate createdAt;
}
