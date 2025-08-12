package com.preet.bookingrental.Controller;


import com.preet.bookingrental.Entities.Listing;
import com.preet.bookingrental.Entities.User;
import com.preet.bookingrental.Repositories.ListingRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/listings")
@AllArgsConstructor
public class ListingController {

    private final ListingRepository listingRepository;

    @GetMapping
    public ResponseEntity<Iterable<Listing>> findAll() {

        Iterable<Listing> listings = listingRepository.findAll();
        return ResponseEntity.ok(listings);

    }

    @PostMapping
    public ResponseEntity<Listing> create(@Valid @RequestBody Listing listing) {
        return ResponseEntity.ok(listingRepository.save(listing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Listing> delete(@RequestParam UUID id) {
        listingRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
