package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // TODO: optimization, tests, comments

        // Unfiltered flights
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("\nTotal flights:");
        for (Flight flight : flights) {
            System.out.println(flight);
        }

        System.out.println("\nApplying every filter separately...");

        Filter filterUpcoming = new FilterDepartureByTime(LocalDateTime.now());
        Filter filterDepartureBeforeArrival = new FilterDepartureBeforeArrival();
        Filter filterLessTwoHoursTotalGroundTime = new FilterTotalGroundTime(7200);

        FilterBuilder filterBuilder = new FilterBuilder();

        // исключить: вылет до текущего момента времени
        System.out.println("\nUpcoming flights:");

        filterBuilder.replace(filterUpcoming);
        List<Flight> flightsUpcoming = filterBuilder.filter(flights);

        for (Flight flight : flightsUpcoming) {
            System.out.println(flight);
        }

        // исключить: сегменты с датой прилёта раньше даты вылета
        System.out.println("\nDeparture-before-arrival segments:");

        filterBuilder.replace(filterDepartureBeforeArrival);
        List<Flight> flightsDepartureBeforeArrival = filterBuilder.filter(flights);

        for (Flight flight : flightsDepartureBeforeArrival) {
            System.out.println(flight);
        }

        // исключить: общее время, проведённое на земле превышает два часа
        System.out.println("\nLess-two-hours-total-ground-time flights:");

        filterBuilder.replace(filterLessTwoHoursTotalGroundTime);
        List<Flight> flightsLessTwoHoursTotalGroundTime = filterBuilder.filter(flights);

        for (Flight flight : flightsLessTwoHoursTotalGroundTime) {
            System.out.println(flight);
        }

        // применяем все фильтры сразу
        System.out.println("\nNow applying every filter we used before:");

        filterBuilder.replace(filterUpcoming, filterDepartureBeforeArrival, filterLessTwoHoursTotalGroundTime);
        List<Flight> flightsFiltered = filterBuilder.filter(flights);

        for (Flight flight : flightsFiltered) {
            System.out.println(flight);
        }

    }
}