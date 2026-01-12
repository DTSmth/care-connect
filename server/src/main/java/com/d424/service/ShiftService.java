package com.d424.service;

import com.d424.dao.ShiftRepository;
import com.d424.model.Shift;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ShiftService {

    private final ShiftRepository shiftRepository;

    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    public List<Shift> getAllShifts() {
        return shiftRepository.findAll();
    }

    public Optional<Shift> getShiftById(int id) {
        return shiftRepository.findById(id);
    }

    public List<Shift> getShiftsByClientId(int clientId) {
        return shiftRepository.findByClient_ClientId(clientId);
    }

    public List<Shift> getShiftsByServiceId(int serviceId) {
        return shiftRepository.findByService_ServicesId(serviceId);
    }

    public List<Shift> getShiftsByZipcode(String zipcode) {
        return shiftRepository.findByZipcode(zipcode);
    }

    public List<Shift> getShiftsByTotalHoursRange(int min, int max) {
        return shiftRepository.findByTotalHoursBetween(min, max);
    }

    public List<Shift> getAvailableShifts() {
        return shiftRepository.findByAvailableTrue();
    }

    @Transactional
    public Shift createShift(Shift shift) {
        return shiftRepository.save(shift);
    }

    @Transactional
    public Shift updateShift(int shiftId, Shift updatedShift) {
        Shift existingShift = shiftRepository.findById(shiftId)
                .orElseThrow(() -> new IllegalArgumentException("Shift not found"));

        existingShift.setClient(updatedShift.getClient());
        existingShift.setService(updatedShift.getService());
        existingShift.setTotalHours(updatedShift.getTotalHours());
        existingShift.setZipcode(updatedShift.getZipcode());
        existingShift.setAvailable(updatedShift.isAvailable());

        return shiftRepository.save(existingShift);
    }


    @Transactional
    public void deleteShift(int shiftId) {
        shiftRepository.deleteById(shiftId);
    }
}
