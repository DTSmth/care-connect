package com.d424.controller;

import com.d424.model.Shift;
import com.d424.service.ShiftService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/shifts")
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ShiftController {

    private final ShiftService shiftService;

    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @GetMapping
    public List<Shift> getAllShifts(
            @RequestParam(required = false) Integer clientId,
            @RequestParam(required = false) Integer serviceId,
            @RequestParam(required = false) String zipcode,
            @RequestParam(required = false) Boolean available,
            @RequestParam(required = false) Integer minHours,
            @RequestParam(required = false) Integer maxHours
    ) {
        if (clientId != null) {
            return shiftService.getShiftsByClientId(clientId);
        } else if (serviceId != null) {
            return shiftService.getShiftsByServiceId(serviceId);
        } else if (zipcode != null) {
            return shiftService.getShiftsByZipcode(zipcode);
        } else if (available != null && available) {
            return shiftService.getAvailableShifts();
        } else if (minHours != null && maxHours != null) {
            return shiftService.getShiftsByTotalHoursRange(minHours, maxHours);
        } else {
            return shiftService.getAllShifts();
        }
    }

    @GetMapping("/{shiftId}")
    public Shift getShiftById(@PathVariable int shiftId) {
        return shiftService.getShiftById(shiftId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shift not found"));
    }

    @PostMapping
    public Shift createShift(@RequestBody Shift shift) {
        return shiftService.createShift(shift);
    }

    @PutMapping("/{shiftId}")
    public Shift updateShift(@PathVariable int shiftId, @RequestBody Shift shift) {
        return shiftService.updateShift(shiftId, shift);
    }

    @DeleteMapping("/{shiftId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShift(@PathVariable int shiftId) {
        shiftService.deleteShift(shiftId);
    }
}
