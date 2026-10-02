package com.logistransport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistransport.model.Tracking;

public interface TrackingRepository  extends JpaRepository<Tracking, Long>{

}
