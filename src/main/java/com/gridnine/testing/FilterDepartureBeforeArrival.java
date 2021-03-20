package com.gridnine.testing;

public class FilterDepartureBeforeArrival implements Filter {
    public boolean isPassing(Flight flight) {
        for (Segment segment : flight.getSegments()) {
            if (segment.getDepartureDate().isAfter(segment.getArrivalDate())) {
                return false;
            }
        }
        return true;
    }

    public FilterType getType() {
        return FilterType.DEPARTURE_BEFORE_ARRIVAL;
    }
}
