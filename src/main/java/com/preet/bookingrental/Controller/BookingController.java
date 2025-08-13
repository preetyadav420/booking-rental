package com.preet.bookingrental.Controller;

import com.preet.bookingrental.Dtos.BookingDto;
import com.preet.bookingrental.Entities.Booking;
import com.preet.bookingrental.Entities.Listing;
import com.preet.bookingrental.Entities.User;
import com.preet.bookingrental.Repositories.BookingRepository;
import com.preet.bookingrental.Repositories.ListingRepository;
import com.preet.bookingrental.Repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {


    private final UserRepository userRepository;
    private final ListingRepository listingRepository;
    private final BookingRepository bookingRepository;

    @PostMapping
    public ResponseEntity<?> createBooking(@Valid @RequestBody BookingDto bookingDto) {

        User user = userRepository.findById(UUID.fromString(bookingDto.getUserId()))
                .orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body(Map.of("error","User Not Found."));
        }

        Listing listing = listingRepository.findById(UUID.fromString(bookingDto.getListingId()))
                .orElse(null);
        if (listing == null) {
            return ResponseEntity.badRequest().body(Map.of("error","Listing Not Found."));
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setListing(listing);
        booking.setStartDate(bookingDto.getStartDate());
        booking.setEndDate(bookingDto.getEndDate());
        booking.setTotalPrice(bookingDto.getTotalPrice());
        booking.setStatus(bookingDto.getStatus());

        return ResponseEntity.ok(bookingRepository.save(booking));
    }

    @GetMapping
    public ResponseEntity<Iterable<Booking>> getBookings() {

        return ResponseEntity.ok(bookingRepository.findAll());

    }
}
