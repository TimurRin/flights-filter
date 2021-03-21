package com.gridnine.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FilterBuilderTest {
    FilterBuilder filterBuilder;

    @BeforeEach
    void setUp() {
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
}
