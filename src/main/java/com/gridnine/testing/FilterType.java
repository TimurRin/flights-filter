package com.gridnine.testing;

public enum FilterType {
    /**
     * Filters flights which have every segment's departure time after specific date and time
     */
    DEPARTURE_BY_TIME,
    /**
     * Filters flights which have correct segments where departure is before arrival
     */
    DEPARTURE_BEFORE_ARRIVAL,
    /**
     * Filters flights which have total ground time is less or equal to specified time
     */
    TOTAL_GROUND_TIME
}
