package com.d424.dao;

import com.d424.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer> {

    // By client
    List<Shift> findByClient_ClientId(int clientId);

    // By service
    List<Shift> findByService_ServicesId(int id);
    // By zipcode
    List<Shift> findByZipcode(String zipcode);

    // Only available shifts
    List<Shift> findByAvailableTrue();

    // Filter by total hours range
    List<Shift> findByTotalHoursBetween(int min, int max);


}
