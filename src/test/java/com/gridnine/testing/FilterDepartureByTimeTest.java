package com.gridnine.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class FilterDepartureByTimeTest {

    private Filter filter;

    private Flight flightOutdated;
    private Flight flightAirborne;
    private Flight flightUpcoming;
    private Flight flightGrounded;
    private Flight flightOutdatedMultiSegmented;

    @BeforeEach
    public void setUp() {
        LocalDateTime now = LocalDateTime.now();

        filter = new FilterDepartureByTime(now);

        flightOutdated = FlightBuilder.createFlight(now.minusHours(14), now.minusHours(6));
        flightAirborne = FlightBuilder.createFlight(now.minusHours(1), now.plusHours(2));
        flightUpcoming = FlightBuilder.createFlight(now.plusHours(5), now.plusHours(8));

        flightGrounded = FlightBuilder.createFlight(now.minusHours(14), now.minusHours(6), now.plusHours(5), now.plusHours(8));
        flightOutdatedMultiSegmented = FlightBuilder.createFlight(now.minusHours(56), now.minusHours(43), now.plusHours(22), now.plusHours(17));
    }

    @Test
    @DisplayName("Outdated flight should fail")
    void shouldFailIfFlightIsOutdated() {
        assertFalse(filter.isPassing(flightOutdated));
    }

    @Test
    @DisplayName("Airborne flight should fail")
    void shouldFailIfFlightIsAirborne() {
        assertFalse(filter.isPassing(flightAirborne));
    }

    @Test
    @DisplayName("Upcoming flight should pass")
    void shouldPassIfFlightIsUpcoming() {
        assertTrue(filter.isPassing(flightUpcoming));
    }

    @Test
    @DisplayName("Grounded flight should fail")
    void shouldFailIfFlightIsGrounded() {
        assertFalse(filter.isPassing(flightGrounded));
    }

    @Test
    @DisplayName("Outdated multi-segment flight should fail")
    void shouldFailIfMultiSegmentedFlightIsOutdated() {
        assertFalse(filter.isPassing(flightOutdatedMultiSegmented));
    }


}
