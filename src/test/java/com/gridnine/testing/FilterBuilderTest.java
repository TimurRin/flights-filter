package com.gridnine.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class FilterBuilderTest {
    private FilterBuilder filterBuilder;

    @BeforeEach
    void createFilter() {
        filterBuilder = new FilterBuilder();
    }

    @Test
    void shouldPassIfAddFilter() {
        Filter filter = new FilterDepartureBeforeArrival();

        filterBuilder.add(filter);

        assertEquals(filter, filterBuilder.get(FilterType.DEPARTURE_BEFORE_ARRIVAL));
    }

    @Test
    void shouldPassIfAddFilterOfSameType() {
        Filter filterOld = new FilterTotalGroundTime(3600);
        Filter filterNew = new FilterTotalGroundTime(7200);

        filterBuilder.add(filterOld);
        filterBuilder.add(filterNew);

        assertEquals(filterNew, filterBuilder.get(FilterType.TOTAL_GROUND_TIME));
    }

    @Test
    void shouldPassIfReplace() {
        Filter filter1 = new FilterDepartureBeforeArrival();
        Filter filter2 = new FilterTotalGroundTime(1800);

        filterBuilder.add(filter1);
        filterBuilder.replace(filter2);

        assertNull(filterBuilder.get(FilterType.DEPARTURE_BEFORE_ARRIVAL));
        assertEquals(filter2, filterBuilder.get(FilterType.TOTAL_GROUND_TIME));
    }

    @Test
    void shouldPassIfRemove() {
        Filter filter1 = new FilterDepartureBeforeArrival();
        Filter filter2 = new FilterTotalGroundTime(1800);

        filterBuilder.add(filter1, filter2);
        filterBuilder.remove(FilterType.DEPARTURE_BEFORE_ARRIVAL);

        assertNull(filterBuilder.get(FilterType.DEPARTURE_BEFORE_ARRIVAL));
        assertEquals(filter2, filterBuilder.get(FilterType.TOTAL_GROUND_TIME));
    }

    @Test
    void shouldPassIfReset() {
        Filter filter1 = new FilterDepartureBeforeArrival();
        Filter filter2 = new FilterTotalGroundTime(1800);

        filterBuilder.add(filter1, filter2);
        filterBuilder.reset();

        assertNull(filterBuilder.get(FilterType.DEPARTURE_BEFORE_ARRIVAL));
        assertNull(filterBuilder.get(FilterType.TOTAL_GROUND_TIME));
    }

    private void applyAllFilters() {
        filterBuilder.replace(new FilterDepartureByTime(LocalDateTime.now()), new FilterDepartureBeforeArrival(), new FilterTotalGroundTime(7200));
    }

    @Test
    void testFlights1k() {
        List<Flight> flights = FlightBuilder.createFlightsBulk(1000);

        applyAllFilters();

        filterBuilder.filter(flights);
    }

    @Test
    void testFlights10k() {
        List<Flight> flights = FlightBuilder.createFlightsBulk(10000);

        applyAllFilters();

        filterBuilder.filter(flights);
    }

    @Test
    void testFlights100k() {
        List<Flight> flights = FlightBuilder.createFlightsBulk(100000);

        applyAllFilters();

        filterBuilder.filter(flights);
    }

    @Test
    void testFlights1000k() {
        List<Flight> flights = FlightBuilder.createFlightsBulk(1000000);

        applyAllFilters();

        filterBuilder.filter(flights);
    }
}
