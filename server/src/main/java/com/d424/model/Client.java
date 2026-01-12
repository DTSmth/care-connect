package com.d424.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private int clientId;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "has_personal_care")
    private boolean hasPersonalCare;
    @Column(name = "has_lifting")
    private boolean hasLifting;

    @Column(name = "address_1")
    private String address1;
    @Column(name = "address_2")
    private String address2;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Shift> shifts;

    @ManyToMany
    @JoinTable(
            name = "client_service",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    @JsonIgnore
    private Set<Services> services;

    public Client(int clientId, String firstName, String lastName, boolean hasPersonalCare, boolean hasLifting, String address1, String address2, String zipcode, String phoneNumber) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hasPersonalCare = hasPersonalCare;
        this.hasLifting = hasLifting;
        this.address1 = address1;
        this.address2 = address2;
        this.zipcode = zipcode;
        this.phoneNumber = phoneNumber;
    }

    public Client() {
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String setFirstName(String firstName) {
        return this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String setLastName(String lastName) {
        return this.lastName = lastName;
    }

    public boolean isHasPersonalCare() {
        return hasPersonalCare;
    }

    public void setHasPersonalCare(boolean hasPersonalCare) {
        this.hasPersonalCare = hasPersonalCare;
    }

    public boolean isHasLifting() {
        return hasLifting;
    }

    public void setHasLifting(boolean hasLifting) {
        this.hasLifting = hasLifting;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", name='" + getName() + '\'' +
                ", hasPersonalCare=" + hasPersonalCare +
                ", hasLifting=" + hasLifting +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return clientId == client.clientId && hasPersonalCare == client.hasPersonalCare && hasLifting == client.hasLifting && Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName) && Objects.equals(address1, client.address1) && Objects.equals(address2, client.address2) && Objects.equals(zipcode, client.zipcode) && Objects.equals(phoneNumber, client.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, firstName, lastName, hasPersonalCare, hasLifting, address1, address2, zipcode, phoneNumber);
    }
}
