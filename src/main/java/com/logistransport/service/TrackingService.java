package com.logistransport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.logistransport.model.Tracking;
import com.logistransport.repository.TrackingRepository;

@Service
public class TrackingService {
	
	private final TrackingRepository trackingRepository;

	public TrackingService(TrackingRepository trackingRepository) {
		this.trackingRepository = trackingRepository;
	}
	
	 public List<Tracking> getAllTracking() {
	        return trackingRepository.findAll();
	    }

	    public Optional<Tracking> getTrackingById(Long id) {
	        return trackingRepository.findById(id);
	    }

	    public Tracking createTracking(Tracking tracking) {
	        return trackingRepository.save(tracking);
	    }

	    public Tracking updateTracking(
	            Long id,
	            Tracking trackingDetails) {

	        Optional<Tracking> optionalTracking =
	                trackingRepository.findById(id);

	        if (optionalTracking.isPresent()) {

	            Tracking tracking =
	                    optionalTracking.get();

	            tracking.setShipmentId(
	                    trackingDetails.getShipmentId()
	            );

	            tracking.setLocation(
	                    trackingDetails.getLocation()
	            );

	            tracking.setStatus(
	                    trackingDetails.getStatus()
	            );

	            tracking.setTrackingDate(
	                    trackingDetails.getTrackingDate()
	            );

	            return trackingRepository.save(tracking);
	        }

	        return null;
	    }

	    public boolean deleteTracking(Long id) {

	        Optional<Tracking> tracking =
	                trackingRepository.findById(id);

	        if (tracking.isPresent()) {

	            trackingRepository.delete(tracking.get());
	            trackingRepository.flush();

	            return true;
	        }

	        return false;
	    }

}
