package com.gridnine.testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterBuilder {
    private List<Filter> filters;

    FilterBuilder(final Filter... filters) {
        this.filters = new ArrayList<>(filters.length);
        this.filters.addAll(Arrays.asList(filters));
    }

    List<Flight> filter(List<Flight> unfilteredFlights) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : unfilteredFlights) {
            boolean isFlightPassing = true;
            for (Filter filter : filters) {
                if (!filter.isPassing(flight)) {
                    isFlightPassing = false;
                    break;
                }
            }
            if (isFlightPassing) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }
}
