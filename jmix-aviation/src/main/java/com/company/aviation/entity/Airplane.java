package com.company.aviation.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Entity
@Table(name = "AIRPLANE", indexes = {
        @Index(name = "IDX_AIRPLANE_AIRPORT", columnList = "AIRPORT_ID")
})
public class Airplane {

    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Integer version;

    @Column(name = "MANUFACTURER")
    private String manufacturer;

    @InstanceName
    @Column(name = "BOARD_NUMBER")
    private String boardNumber;

    @Column(name = "PAYLOAD_CAPACITY")
    private Double payloadCapacity;

    @Column(name = "COMMISSIONING_DATE")
    private LocalDate commissioningDate;

    @JoinColumn(name = "AIRPORT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Airport airport;

    @JoinTable(name = "AIRPLANE_FLIGHT_LINK",
            joinColumns = @JoinColumn(name = "AIRPLANE_ID"),
            inverseJoinColumns = @JoinColumn(name = "FLIGHT_ID"))
    @ManyToMany
    private List<Flight> flights = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getBoardNumber() {
        return boardNumber;
    }

    public void setBoardNumber(String boardNumber) {
        this.boardNumber = boardNumber;
    }

    public Double getPayloadCapacity() {
        return payloadCapacity;
    }

    public void setPayloadCapacity(Double payloadCapacity) {
        this.payloadCapacity = payloadCapacity;
    }

    public LocalDate getCommissioningDate() {
        return commissioningDate;
    }

    public void setCommissioningDate(LocalDate commissioningDate) {
        this.commissioningDate = commissioningDate;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public String toString() {
        return boardNumber;
    }
}
