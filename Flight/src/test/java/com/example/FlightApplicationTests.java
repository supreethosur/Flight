package com.example;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.Advice.CustomException;
import com.example.Controller.FlightController;
import com.example.Entity.Flight;
import com.example.Service.FlightService;

@SpringBootTest
class FlightApplicationTests {

	
	@Autowired
	FlightController flightController;
	
	
	@MockBean
	FlightService flightService;
	
	@Test
	void checkFlightNotNull() throws CustomException {
		
		Integer id=2;
		Flight flight=new Flight();
		Mockito.when(flightService.findById(2)).thenReturn(flight);
		Assertions.assertNotNull(flightController.getFlightsById(2));
		
	}
	
	
	@Test
	void checkFlightNull() throws CustomException {
		
		Mockito.when(flightService.findById(2)).thenReturn(null);
		
		
		Assertions.assertNull(flightController.getFlightsById(2));
		
	}
	
	
	@Test
	void checkFlightBasedonSchedule() throws Exception {
		List<Flight> flights = new ArrayList<>();
		Flight flight=new Flight();
		flight.setAirline("newAirline");
		flights.add(flight);
		
		Mockito.when(flightService.findByScheduleType("WD")).thenReturn(flights);
		
		Assertions.assertEquals(flightController.getFlightsByScheduleType("WD").size(),1);
	}
	
	
	
	
}