package com.preet.bookingrental.Repositories;

import com.preet.bookingrental.Entities.Listing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ListingRepository extends CrudRepository<Listing, UUID> {

    Iterable<Listing> findAllByVendor_Id(UUID id);
}
