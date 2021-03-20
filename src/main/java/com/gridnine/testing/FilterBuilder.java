package com.gridnine.testing;

import java.util.ArrayList;
import java.util.List;

public class FilterBuilder {
    private Filter[] filters = new Filter[FilterType.values().length];

    FilterBuilder() {
        reset();
    }

    FilterBuilder(final Filter... newFilters) {
        replace(newFilters);
    }

    Filter get(FilterType filterType) {
        return filters[filterType.ordinal()];
    }

    void add(final Filter... newFilters) {
        for (Filter filter : newFilters) {
            filters[filter.getType().ordinal()] = filter; // replace old filters of the same type
        }
    }

    void replace(final Filter... newFilters) {
        reset();
        add(newFilters);
    }

    void remove(FilterType filterType) {
        filters[filterType.ordinal()] = null;
    }

    void reset() {
        filters = new Filter[FilterType.values().length];
    }

    List<Flight> filter(List<Flight> unfilteredFlights) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : unfilteredFlights) {
            boolean isFlightPassing = true;
            for (Filter filter : filters) {
                if (filter != null) {
                    if (!filter.isPassing(flight)) {
                        isFlightPassing = false;
                        break;
                    }
                }
            }
            if (isFlightPassing) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }
}
