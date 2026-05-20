package com.company.aviation.service;

import com.company.aviation.entity.Airplane;
import com.company.aviation.entity.Flight;
import io.jmix.core.DataManager;
import io.jmix.core.entity.KeyValueEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AviationService {

    private final DataManager dataManager;

    public AviationService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public Double getAveragePayloadCapacityByJpql() {
        KeyValueEntity result = dataManager.loadValues(
                        "select avg(a.payloadCapacity) from Airplane a")
                .properties("avgCapacity")
                .one();
        return result.getValue("avgCapacity");
    }

    public Double getMaxFlightDurationExplicit() {
        return dataManager.load(Flight.class)
                .all()
                .list()
                .stream()
                .map(Flight::getFlightDuration)
                .filter(duration -> duration != null)
                .max(Comparator.naturalOrder())
                .orElse(null);
    }

    public Optional<Airplane> getAirplaneById(UUID id) {
        return dataManager.load(Airplane.class)
                .id(id)
                .optional();
    }

    public List<Flight> getFlightsByAirport(UUID airportId) {
        return dataManager.load(Flight.class)
                .query("select f from Flight f where f.airport.id = :airportId")
                .parameter("airportId", airportId)
                .list();
    }
}
