package com.preet.bookingrental.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @NotNull
    private String title;

    private String description;

    @NotNull
    @Column(name = "price_per_day")
    private BigDecimal pricePerDay;

    @Column(name="created_at", insertable = false, updatable = false)
    private LocalDate createdAt;
}
