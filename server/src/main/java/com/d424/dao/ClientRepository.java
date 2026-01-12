package com.d424.dao;

import com.d424.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    // Find by exact first and last name
    List<Client> findByFirstNameAndLastName(String firstName, String lastName);

    // Case-insensitive search
    List<Client> findByFirstNameIgnoreCase(String firstName);
    List<Client> findByLastNameIgnoreCase(String lastName);
    List<Client> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String first, String last);

    // Search by zipcode
    List<Client> findByZipcode(String zipcode);

    // Filters
    List<Client> findByHasPersonalCareTrue();
    List<Client> findByHasLiftingTrue();
}
