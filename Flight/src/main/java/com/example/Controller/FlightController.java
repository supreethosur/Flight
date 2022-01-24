package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Advice.CustomException;
import com.example.Entity.Flight;
import com.example.Model.FlightModel;
import com.example.Model.NotificationModel;
import com.example.Service.FlightService;

@RestController
@CrossOrigin(origins = "*")
public class FlightController {
	
@Autowired
FlightService flightService;

	@GetMapping("/{Id}")
	public  Flight  getFlightsById(@PathVariable Integer Id) throws CustomException {
		System.out.println("inside Controller");
		Flight flights = flightService.findById(Id);	 
		return flights;
	} 
	
	@GetMapping("/scheduleType/{scheduleType}")
	public  List<Flight>  getFlightsByScheduleType(@PathVariable String scheduleType) throws Exception {
		System.out.println("inside Controller");
		List<Flight> flights = flightService.findByScheduleType(scheduleType);	 
		return flights;
	}
	
	@PostMapping("/addFlights")
	public FlightModel addFlights(@RequestBody FlightModel ipflight) throws Exception {
		System.out.println(123);
		return flightService.addFlight(ipflight);
	}
	
	@GetMapping("/GetAllFlights")
	public  List<FlightModel>  getFlightsByName(String FlightName) throws Exception {
		System.out.println("inside Controller");
		List<FlightModel> flights = flightService.findAll(FlightName);	 
		return flights;
	}
	
	
	@DeleteMapping("/deleteFlight/{Id}")
	public void deleteFlight(@PathVariable Integer Id) throws Exception {
		System.out.println(Id);
		flightService.deleteById(Id);
	}
	
	@PutMapping("/updateFlight")
	public FlightModel updateFlight(@RequestBody FlightModel flight) throws Exception {
		FlightModel flight1=flightService.updateFlight(flight);
		return flight1;
	}
	
//	@KafkaListener(topics = "new_kafkaTopic", groupId="group_id", containerFactory = "userKafkaListenerFactory")
//	public void consumeJson(NotificationModel model) {
//	    System.out.println("Consumed JSON Message: " + model.getMessage());
//	    flightService.saveNotification(model);
//	   
//	}
	
	
	
}
