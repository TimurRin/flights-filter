package com.gridnine.testing;

import java.time.LocalDateTime;

public class FilterDepartureByTime implements Filter {
    private LocalDateTime dateTime;

    FilterDepartureByTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isPassing(Flight flight) {
        for (Segment segment : flight.getSegments()) {
            if (segment.getDepartureDate().isAfter(dateTime)) {
                return true;
            }
        }
        return false;
    }

    public FilterType getType() {
        return FilterType.DEPARTURE_BY_TIME;
    }
}
