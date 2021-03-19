package com.gridnine.testing;

public abstract class Filter implements FilterInterface {
    public boolean isPassing(Flight flight) {
        return true;
    }
}
