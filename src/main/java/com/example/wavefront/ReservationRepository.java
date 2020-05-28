package com.example.wavefront;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

interface ReservationRepository extends ReactiveCrudRepository<Reservation, String> {

}
