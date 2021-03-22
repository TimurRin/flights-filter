package com.gridnine.testing;

import java.util.ArrayList;
import java.util.List;

/**
 * A
 */
public class FilterBuilder {
    private Filter[] filters = new Filter[FilterType.values().length];

    FilterBuilder() {
        reset();
    }

    /**
     * @param filterType FilterType to get
     * @return Filter instance
     */
    Filter get(FilterType filterType) {
        return filters[filterType.ordinal()];
    }

    /**
     * Adding new filters over existing ones
     *
     * @param newFilters Vararg of filters
     */
    void add(final Filter... newFilters) {
        for (Filter filter : newFilters) {
            filters[filter.getType().ordinal()] = filter; // replace old filters of the same type
        }
    }

    /**
     * Removing every old filter and adding new ones
     *
     * @param newFilters Vararg of filters
     */
    void replace(final Filter... newFilters) {
        reset();
        add(newFilters);
    }

    /**
     * Removes filter of specified FilterType
     *
     * @param filterType FilterType to remove
     */
    void remove(FilterType filterType) {
        filters[filterType.ordinal()] = null;
    }

    /**
     * Prunes all filters from FilterBuilder instance
     */
    void reset() {
        filters = new Filter[FilterType.values().length];
    }

    /**
     * @param unfilteredFlights List of flights needed to be filtered
     * @return List with filtered flights
     */
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
