package com.d424.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "shift", uniqueConstraints = {@UniqueConstraint(name = "uq_shift", columnNames = {"client_id", "services_id", "total_hours", "zipcode"})})
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shift_id")
    private int shiftId;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "services_id", nullable = false)
    private Services service;

    @Column(name = "total_hours", nullable = false)
    private int totalHours;

    @Column(nullable = false)
    private String zipcode;

    @Column(name = "available", nullable = false)
    private boolean available;

    public Shift() {
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shift)) return false;
        Shift shift = (Shift) o;
        return shiftId == shift.shiftId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shiftId);
    }

    @Override
    public String toString() {
        return "Shift{" +
                "shiftId=" + shiftId +
                ", client=" + client +
                ", service=" + service +
                ", totalHours=" + totalHours +
                ", zipcode='" + zipcode + '\'' +
                ", available=" + available +
                '}';
    }
}
