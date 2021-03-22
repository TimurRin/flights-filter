package com.gridnine.testing;

import java.time.LocalDateTime;

/**
 * Filters flights which have every segment's departure time after specific date and time
 */
public class FilterDepartureByTime implements Filter {
    private final LocalDateTime dateTime;

    FilterDepartureByTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isPassing(Flight flight) {
        for (Segment segment : flight.getSegments()) {
            if (segment.getDepartureDate().isBefore(dateTime)) {
                return false;
            }
        }
        return true;
    }

    public FilterType getType() {
        return FilterType.DEPARTURE_BY_TIME;
    }
}
