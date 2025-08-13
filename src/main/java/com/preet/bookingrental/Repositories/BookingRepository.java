package com.preet.bookingrental.Repositories;

import com.preet.bookingrental.Entities.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookingRepository extends CrudRepository<Booking, UUID> {
}
