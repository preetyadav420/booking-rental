package com.preet.bookingrental.Controller;


import com.preet.bookingrental.Dtos.CreateListingDto;
import com.preet.bookingrental.Entities.Listing;
import com.preet.bookingrental.Entities.User;
import com.preet.bookingrental.Repositories.ListingRepository;
import com.preet.bookingrental.Repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @GetMapping("/mylistings")
    public ResponseEntity<?> findAllByVendor() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getPrincipal().toString();

        User vendor = userRepository.findUserByUsername(username).orElse(null);
        if(vendor == null)
        {
            return ResponseEntity.badRequest().body(Map.of("error","Vendor Not Found."));
        }
        Iterable<Listing> listings = listingRepository.findAllByVendor_Id(vendor.getId());
        return ResponseEntity.ok(listings);

    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateListingDto listingDto) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getPrincipal().toString();

        User vendor = userRepository.findUserByUsername(username).orElse(null);

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
