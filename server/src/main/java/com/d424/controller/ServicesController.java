package com.d424.controller;

import com.d424.model.Services;
import com.d424.service.ServicesService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/services")
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ServicesController {

    private final ServicesService serviceService;

    public ServicesController(ServicesService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public List<Services> getAllServices() {
        return serviceService.getAllServices();
    }

    @GetMapping("/{serviceId}")
    public Services getServiceById(@PathVariable int serviceId) {
        return serviceService.getServiceById(serviceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found"));
    }

    @GetMapping("/search")
    public Services getServiceByName(@RequestParam String name) {
        return serviceService.getServiceByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found"));
    }

    @PostMapping
    public Services createService(@RequestBody Services service) {
        return serviceService.createService(service);
    }

    @PutMapping("/{serviceId}")
    public Services updateService(@PathVariable int serviceId, @RequestBody Services service) {
        return serviceService.updateService(serviceId, service);
    }

    @DeleteMapping("/{serviceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteService(@PathVariable int serviceId) {
        serviceService.deleteService(serviceId);
    }
}
