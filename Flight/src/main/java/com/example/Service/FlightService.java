package com.example.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.Entity.Flight;
import com.example.Model.Journey;
import com.example.Model.FlightModel;
import com.example.Repository.FlightsRepository;

public class FlightService {
	
	@Autowired
	FlightsRepository flightRepo;
	
	@Autowired
	RestTemplate restTemplate;

//	@Cacheable(key ="#id",value = "flightStore")
	public Flight findById(Integer id) throws Exception {
		System.out.println("inside service");
		Optional<Flight> flights = flightRepo.findById(id);
		if(flights.isPresent()) {
			return flights.get();
		}else {
			throw new Exception("No Flight with the flight Id " + id);
		}
	}
	public FlightModel addFlight(FlightModel ipflight) throws Exception {
		try {
			Flight flight=addFlightDetails(ipflight);
			ipflight.setFlightId(flight.getFlightId());
			
			String url="http://localhost:8085/FlightBooking/addJourney";
			ParameterizedTypeReference<Journey> responseType= new ParameterizedTypeReference<Journey>() {
			};
			
			HttpEntity<FlightModel> httpEntity=new HttpEntity<>(ipflight,null);
			ResponseEntity<Journey> res1=restTemplate.exchange(url, HttpMethod.POST, httpEntity, responseType);
			
			
			
			Journey journey=res1.getBody();//addJourneyDetails(ipflight);
			ipflight.setJourneyId(journey.getJourneyId());

			return ipflight;

		}
		catch(Exception e) {
			throw new Exception("Something went wrong");
		}
	}

	public Flight addFlightDetails(FlightModel ipflight) throws Exception {
		try {
			Flight flight=new Flight();
			flight.setFlightName(ipflight.getFlightName());
			flight.setBusinessSeats(ipflight.getBusinessSeats());
			flight.setNonBusinessSeats(ipflight.getNonBusinessSeats());
			flight.setScheduleType(ipflight.getScheduleType());
			return flightRepo.save(flight);
		}
		catch(Exception e) {
			throw new Exception("Something went wrong");
		}
	}
	
	public void deleteById(Integer id) throws Exception {
		try {
			Flight flight=flightRepo.findByFlightId(id);
			flight.setIsActive(0);
			flightRepo.save(flight);	
		}
		catch(Exception e) { 
			throw new Exception("Something went wrong while deleting");
		}
	}
	
	public FlightModel updateFlight( FlightModel flight) throws Exception {
		Flight flights = flightRepo.findByFlightId(flight.getFlightId());
		if(flights!=null) {
			flights.setFlightName(flight.getFlightName());
			flights.setBusinessSeats(flight.getBusinessSeats());
			flights.setNonBusinessSeats(flight.getNonBusinessSeats());
			flights.setScheduleType(flight.getScheduleType());
			flights.setAirline(flight.getAirline());
			flightRepo.save(flights);

			
			String url="http://localhost:8085/FlightBooking/addJourney";
			ParameterizedTypeReference<Journey> responseType= new ParameterizedTypeReference<Journey>() {
			};
			
			HttpEntity<FlightModel> httpEntity=new HttpEntity<>(flight,null);
			ResponseEntity<Journey> res1=restTemplate.exchange(url, HttpMethod.POST, httpEntity, responseType);
			
			
			Journey journey=res1.getBody();//journeyRepo.findByJourneyId(flight.getJourneyId());
//			if(journey!=null) {
//				journey.setFlightId(flight.getFlightId());
//				journey.setFromLocation(flight.getFromLocation());
//				journey.setToLocation(flight.getToLocation());
//				journey.setArrivalTime(flight.getArrivalTime());
//				journey.setDepartureTime(flight.getDepartureTime());
//				Journey journey1=journeyRepo.save(journey);	
//			}

			return flight;
		}else {
			throw new Exception("No Flight with the flight Id " + flight.getFlightId());
		} 

	}
	public List<Flight> findByScheduleType(String scheduleType) {
		List<Flight> flight1 = flightRepo.findFlights(scheduleType);
		return flight1;
	}

}
