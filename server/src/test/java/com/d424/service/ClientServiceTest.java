package com.d424.service;

import com.d424.dao.ClientRepository;
import com.d424.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private Client sampleClient;

    @BeforeEach
    void setUp() {
        sampleClient = new Client();
        sampleClient.setClientId(1);
        sampleClient.setFirstName("John");
        sampleClient.setLastName("Doe");
    }

    @Test
    void testGetClientById() {
        // Arrange
        when(clientRepository.findById(1)).thenReturn(Optional.of(sampleClient));

        // Act
        Optional<Client> result = clientService.getClientById(1);

        // Assert
        assertTrue(result.isPresent(), "Client should be present in the Optional");
        assertEquals(1, result.get().getClientId()); // Use .get() to access the Client object
        assertEquals("John", result.get().getFirstName());
    }

    @Test
    void testCreateClient() {
        // Arrange
        when(clientRepository.save(any(Client.class))).thenReturn(sampleClient);

        // Act
        Client created = clientService.createClient(new Client());

        // Assert
        assertNotNull(created);
        assertEquals(1, created.getClientId());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testUpdateClient() {
        // Arrange
        when(clientRepository.save(any(Client.class))).thenReturn(sampleClient);

        // Act
        Client updated = clientService.updateClient(sampleClient);

        // Assert
        assertNotNull(updated);
        assertEquals("Doe", updated.getLastName());
    }
}