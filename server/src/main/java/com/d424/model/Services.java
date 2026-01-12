package com.d424.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "services", uniqueConstraints = {@UniqueConstraint(name = "uc_service_service_name", columnNames = "service_name")})
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "services_id")
    private int servicesId;

    @Column(name = "service_name", nullable = false, length = 40)
    private String serviceName;

    @OneToMany(mappedBy = "service")
    @JsonIgnore
    private List<Shift> shifts;

    @ManyToMany(mappedBy = "services")
    @JsonIgnore
    private Set<Client> clients;

    public Services() {
    }

    public Services(int serviceId, String serviceName) {
        this.servicesId = serviceId;
        this.serviceName = serviceName;
    }

    public int getServicesId() {
        return servicesId;
    }

    public void setServicesId(int serviceId) {
        this.servicesId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Services)) return false;
        Services service = (Services) o;
        return servicesId == service.servicesId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(servicesId);
    }

    @Override
    public String toString() {
        return "Service{" + "serviceId=" + servicesId + ", serviceName='" + serviceName + '\'' + '}';
    }
}
