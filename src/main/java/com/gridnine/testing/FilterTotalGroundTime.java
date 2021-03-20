package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class FilterTotalGroundTime extends Filter {
    private long groundTime;

    FilterTotalGroundTime(long groundTime) {
        this.groundTime = groundTime;
    }

    public boolean isPassing(Flight flight) {
        long groundTime = 0;

        List<Segment> segments = flight.getSegments();
        int segmentsSize = segments.size();

        if (segmentsSize >= 2) {
            for (int i = 1; i < segmentsSize; i++) {
                LocalDateTime previousArrival = segments.get(i - 1).getArrivalDate();
                LocalDateTime currentDeparture = segments.get(i).getDepartureDate();

                long segmentGroundTime = previousArrival.until(currentDeparture, ChronoUnit.SECONDS);

                groundTime += segmentGroundTime;
            }
        }

        return groundTime <= this.groundTime;
    }
}
