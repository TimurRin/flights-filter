package com.gridnine.testing;

public class FilterTotalGroundTime extends Filter {
    private long groundTime;

    FilterTotalGroundTime(long groundTime) {
        this.groundTime = groundTime;
    }

    public boolean isPassing(Flight flight) {
        long groundTime = 0;

        // TODO: implement

        return groundTime <= this.groundTime;
    }
}
