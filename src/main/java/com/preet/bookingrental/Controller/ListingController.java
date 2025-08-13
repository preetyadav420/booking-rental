package com.preet.bookingrental.Controller;


import com.preet.bookingrental.Dtos.ListingDto;
import com.preet.bookingrental.Entities.Listing;
import com.preet.bookingrental.Entities.User;
import com.preet.bookingrental.Repositories.ListingRepository;
import com.preet.bookingrental.Repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/listings")
@AllArgsConstructor
public class ListingController {

    private final ListingRepository listingRepository;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<Iterable<Listing>> findAll() {

        Iterable<Listing> listings = listingRepository.findAll();
        return ResponseEntity.ok(listings);

    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ListingDto listingDto) {

        User vendor = userRepository.findById(UUID.fromString(listingDto.getVendorId()))
                .orElse(null);
        if(vendor == null)
        {
            return ResponseEntity.badRequest().body(Map.of("error","Vendor Not Found."));
        }

        Listing listing = new Listing();
        listing.setVendor(vendor);
        listing.setTitle(listingDto.getTitle());
        listing.setDescription(listingDto.getDescription());
        listing.setPricePerDay(listingDto.getPricePerDay());


        return ResponseEntity.ok(listingRepository.save(listing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Listing> delete(@RequestParam UUID id) {
        listingRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
