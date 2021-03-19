package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // TODO: implement FilterTotalGroundTime filter, optimization, tests

        // Unfiltered flights
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("\nTotal flights:");
        for (Flight flight : flights) {
            System.out.println(flight);
        }

        // исключить: вылет до текущего момента времени
        System.out.println("\nUpcoming flights:");
        Filter filterUpcoming = new FilterDepartureByTime(LocalDateTime.now());
        List<Flight> flightsUpcoming = new FilterBuilder(filterUpcoming).filter(flights);

        for (Flight flight : flightsUpcoming) {
            System.out.println(flight);
        }

        // исключить: сегменты с датой прилёта раньше даты вылета
        System.out.println("\nDeparture-before-arrival segments:");
        Filter filterDepartureBeforeArrival = new FilterDepartureBeforeArrival();
        List<Flight> flightsDepartureBeforeArrival = new FilterBuilder(filterDepartureBeforeArrival).filter(flights);

        for (Flight flight : flightsDepartureBeforeArrival) {
            System.out.println(flight);
        }
//
//        // исключить: общее время, проведённое на земле превышает два часа
//        System.out.println("\nLess-two-hours-total-ground-time flights:");
//        Filter filterLessTwoHoursTotalGroundTime = new FilterTotalGroundTime(7200);
//        List<Flight> flightsLessTwoHoursTotalGroundTime = new FilterBuilder(filterLessTwoHoursTotalGroundTime).filter(flights);
//
//        for (Flight flight : flightsLessTwoHoursTotalGroundTime) {
//            System.out.println(flight);
//        }

        // применяем все фильтры сразу
        System.out.println("\nApplying every filter used before:");
//        List<Flight> flightsFiltered = new FilterBuilder(filterUpcoming, filterDepartureBeforeArrival, filterLessTwoHoursTotalGroundTime).filter(flights);
        List<Flight> flightsFiltered = new FilterBuilder(filterUpcoming, filterDepartureBeforeArrival).filter(flights);

        for (Flight flight : flightsFiltered) {
            System.out.println(flight);
        }

    }
}