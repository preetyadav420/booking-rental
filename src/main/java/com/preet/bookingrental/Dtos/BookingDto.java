package com.preet.bookingrental.Dtos;

import com.preet.bookingrental.Entities.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BookingDto {

    @NotNull
    private String userId;

    @NotNull
    private String listingId;

    private Date startDate;

    private Date endDate;

    private BigDecimal totalPrice;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
}
