package com.gridnine.testing;

public class FilterDepartureBeforeArrival extends Filter {
    public boolean isPassing(Flight flight) {
        for (Segment segment : flight.getSegments()) {
            if (segment.getDepartureDate().isAfter(segment.getArrivalDate())) {
                return false;
            }
        }
        return true;
    }
}
