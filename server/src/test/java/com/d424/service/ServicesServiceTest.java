package com.d424.service;

import com.d424.dao.ServiceRepository;
import com.d424.model.Services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServicesServiceTest {

    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private ServicesService servicesService;

    private Services sampleService;

    @BeforeEach
    void setUp() {
        // Note: Using your Services model which has servicesId and serviceName
        sampleService = new Services(1, "Respite Care");
    }

    @Test
    void testGetServiceById() {
        // Arrange
        when(serviceRepository.findById(1)).thenReturn(Optional.of(sampleService));

        // Act
        Optional<Services> result = servicesService.getServiceById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Respite Care", result.get().getServiceName());
    }

    @Test
    void testCreateService() {
        // Arrange
        when(serviceRepository.save(any(Services.class))).thenReturn(sampleService);

        // Act
        Services created = servicesService.createService(new Services());

        // Assert
        assertNotNull(created);
        assertEquals(1, created.getServicesId());
        verify(serviceRepository, times(1)).save(any(Services.class));
    }

    @Test
    void testUpdateService_Success() {
        // Arrange
        Services updatedInfo = new Services();
        updatedInfo.setServiceName("Advanced Nursing");

        when(serviceRepository.findById(1)).thenReturn(Optional.of(sampleService));
        when(serviceRepository.save(any(Services.class))).thenReturn(sampleService);

        // Act
        Services result = servicesService.updateService(1, updatedInfo);

        // Assert
        assertNotNull(result);
        assertEquals("Advanced Nursing", sampleService.getServiceName()); // Verify existing object was updated
        verify(serviceRepository).save(sampleService);
    }

    @Test
    void testUpdateService_NotFound() {
        // Arrange
        when(serviceRepository.findById(99)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            servicesService.updateService(99, new Services());
        });
        // Verify save was never called because the exception was thrown
        verify(serviceRepository, never()).save(any());
    }

    @Test
    void testDeleteService() {
        // Act
        servicesService.deleteService(1);

        // Assert
        verify(serviceRepository, times(1)).deleteById(1);
    }
}