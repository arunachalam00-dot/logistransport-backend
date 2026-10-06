package com.logistransport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.logistransport.model.Route;
import com.logistransport.repository.RouteRepository;

@Service
public class RouteService {

	private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Optional<Route> getRouteById(Long id) {
        return routeRepository.findById(id);
    }
    
    public Optional<Route> getRouteByShipmentId(String shipmentId) {
        return routeRepository.findByShipmentId(shipmentId);
    }

    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

    public Route updateRoute(
            Long id,
            Route routeDetails) {

        Optional<Route> optionalRoute =
                routeRepository.findById(id);

        if (optionalRoute.isPresent()) {

            Route route =
                    optionalRoute.get();

            route.setRouteName(
                    routeDetails.getRouteName()
            );

            route.setSource(
                    routeDetails.getSource()
            );

            route.setDestination(
                    routeDetails.getDestination()
            );

            route.setDistance(
                    routeDetails.getDistance()
            );

            route.setStatus(
                    routeDetails.getStatus()
            );

            return routeRepository.save(route);
        }

        return null;
    }

    public boolean deleteRoute(Long id) {

        Optional<Route> route =
                routeRepository.findById(id);

        if (route.isPresent()) {

            routeRepository.delete(route.get());
            routeRepository.flush();

            return true;
        }

        return false;
    }
}
