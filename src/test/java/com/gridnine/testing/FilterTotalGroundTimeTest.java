package com.gridnine.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterTotalGroundTimeTest {

    private long groundedTime = 7200;

    private Filter filter;

    private Flight flightGroundedOnceForOneHour;
    private Flight flightGroundedOnceForTwoHours;
    private Flight flightGroundedOnceForThreeHours;

    private Flight flightGroundedTwiceForTwoHours;
    private Flight flightGroundedTwiceForThreeHours;
    private Flight flightGroundedTwiceForSixHours;

    private Flight flightNotGrounded;

    @BeforeEach
    public void setUp() {
        filter = new FilterTotalGroundTime(groundedTime);

        LocalDateTime now = LocalDateTime.now();

        flightGroundedOnceForOneHour = FlightBuilder.createFlight(now.plusHours(1), now.plusHours(3), now.plusHours(4), now.plusHours(10));
        flightGroundedOnceForTwoHours = FlightBuilder.createFlight(now.plusHours(1), now.plusHours(3), now.plusHours(5), now.plusHours(11));
        flightGroundedOnceForThreeHours = FlightBuilder.createFlight(now.plusHours(1), now.plusHours(3), now.plusHours(6), now.plusHours(12));

        flightGroundedTwiceForTwoHours = FlightBuilder.createFlight(
                now.plusHours(1), now.plusHours(3), now.plusHours(4), now.plusHours(10), now.plusHours(11), now.plusHours(17)
        );
        flightGroundedTwiceForThreeHours = FlightBuilder.createFlight(
                now.plusHours(1), now.plusHours(3), now.plusHours(4), now.plusHours(10), now.plusHours(12), now.plusHours(17)
        );
        flightGroundedTwiceForSixHours = FlightBuilder.createFlight(
                now.plusHours(1), now.plusHours(3), now.plusHours(6), now.plusHours(10), now.plusHours(13), now.plusHours(17)
        );

        flightNotGrounded = FlightBuilder.createFlight(now.plusHours(5), now.plusHours(8));
    }

    private String getGroundedTimeString() {
        return "Max grounded time, sec: " + groundedTime;
    }

    @Test
    @DisplayName("Flight grounded once for one hour should pass")
    void shouldPassIfFlightGroundedOnceForOneHour() {
        assertTrue(filter.isPassing(flightGroundedOnceForOneHour), getGroundedTimeString());
    }

    @Test
    @DisplayName("Flight grounded once for two hours should pass")
    void shouldPassIfFlightGroundedOnceForTwoHours() {
        assertTrue(filter.isPassing(flightGroundedOnceForTwoHours), getGroundedTimeString());
    }

    @Test
    @DisplayName("Flight grounded once for three hours should fail")
    void shouldFailIfFlightGroundedOnceForThreeHours() {
        assertFalse(filter.isPassing(flightGroundedOnceForThreeHours), getGroundedTimeString());
    }

    @Test
    @DisplayName("Flight grounded twice for total two hours should pass")
    void shouldPassIfFlightGroundedTwiceForTwoHours() {
        assertTrue(filter.isPassing(flightGroundedTwiceForTwoHours), getGroundedTimeString());
    }

    @Test
    @DisplayName("Flight grounded twice for total three hours should fail")
    void shouldFailIfFlightGroundedTwiceForThreeHours() {
        assertFalse(filter.isPassing(flightGroundedTwiceForThreeHours), getGroundedTimeString());
    }

    @Test
    @DisplayName("Flight grounded twice for total six hours should fail")
    void shouldFailIfFlightGroundedTwiceForSixHours() {
        assertFalse(filter.isPassing(flightGroundedTwiceForSixHours), getGroundedTimeString());
    }

    @Test
    @DisplayName("Non-grounded flight should pass")
    void shouldPassIfFlightIsNonGrounded() {
        assertTrue(filter.isPassing(flightNotGrounded), getGroundedTimeString());
    }
}
