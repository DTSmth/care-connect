package com.d424.service;

import com.d424.dao.ShiftRepository;
import com.d424.model.Shift;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShiftServiceTest {

    @Mock
    private ShiftRepository shiftRepository;

    @InjectMocks
    private ShiftService shiftService;

    @Test
    void testFindAllShifts() {
        // Arrange
        when(shiftRepository.findAll()).thenReturn(List.of(new Shift()));

        // Act
        List<Shift> results = shiftService.getAllShifts();

        // Assert
        assertNotNull(results);
        assertEquals(1, results.size());
        verify(shiftRepository, times(1)).findAll();
    }
}