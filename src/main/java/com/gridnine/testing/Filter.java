package com.gridnine.testing;

public interface Filter {
    boolean isPassing(Flight flight);
    FilterType getType();
}
