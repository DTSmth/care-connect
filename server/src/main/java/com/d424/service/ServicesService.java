package com.d424.service;

import com.d424.dao.ServiceRepository;
import com.d424.model.Services;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesService {

    private final ServiceRepository serviceRepository;

    public ServicesService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<Services> getAllServices() {
        return serviceRepository.findAll();
    }

    public Optional<Services> getServiceById(int id) {
        return serviceRepository.findById(id);
    }

    public Optional<Services> getServiceByName(String name) {
        return serviceRepository.findByServiceNameIgnoreCase(name);
    }

    @Transactional
    public Services createService(Services service) {
        return serviceRepository.save(service);
    }

    @Transactional
    public Services updateService(int id, Services updatedService) {
        Services existingService = serviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Service not found"));

        existingService.setServiceName(updatedService.getServiceName());
        return serviceRepository.save(existingService);
    }

    @Transactional
    public void deleteService(int id) {
        serviceRepository.deleteById(id);
    }
}
