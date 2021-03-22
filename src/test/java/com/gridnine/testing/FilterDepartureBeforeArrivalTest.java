package com.gridnine.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FilterDepartureBeforeArrivalTest {

    private Filter filter;

    private Flight flightCorrect;
    private Flight flightIncorrect;

    @BeforeEach
    void setUp() {
        filter = new FilterDepartureBeforeArrival();

        LocalDateTime arrival = LocalDateTime.now();
        flightCorrect = FlightBuilder.createFlight(arrival.minusHours(1), arrival.plusHours(2));
        flightIncorrect = FlightBuilder.createFlight(arrival, arrival.minusHours(6));
    }

    @Test
    @DisplayName("Correct flight should pass")
    void shouldPassIfFlightIsCorrect() {
        assertTrue(filter.isPassing(flightCorrect));
    }

    @Test
    @DisplayName("Incorrect flight should fail")
    void shouldFailToPassIfFlightIsIncorrect() {
        assertFalse(filter.isPassing(flightIncorrect));
    }
}
