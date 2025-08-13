package com.preet.bookingrental.Dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ListingDto {

    @NotNull
    private String vendorId;

    @NotNull
    private String title;

    private String description;

    @NotNull
    private BigDecimal pricePerDay;
}
