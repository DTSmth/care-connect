package com.d424.dao;

import com.d424.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Services, Integer> {

    boolean existsByServiceName(String serviceName);

    Optional<Services> findByServiceName(String serviceName);
    Optional<Services> findByServiceNameIgnoreCase(String serviceName);

    boolean existsByServiceNameIgnoreCase(String serviceName);
}
