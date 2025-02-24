package com.group2.hospitally.controller;

import com.group2.hospitally.model.entity.Staff;
import com.group2.hospitally.model.request.Staff.CreateStaffRequest;
import com.group2.hospitally.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/staff")
public class StaffController {

    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public ResponseEntity<List<Staff>> getAllStaffs() {
        List<Staff> staffs = staffService.getAllStaffs();
        return new ResponseEntity<>(staffs, HttpStatus.OK);
    }

    @GetMapping("/{staffId}")
    public ResponseEntity<Staff> getStaffById(@PathVariable int staffId) {
        Staff staff = staffService.getStaffById(staffId);
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Staff> createStaff(@RequestBody CreateStaffRequest request) {
        Staff staff = staffService.createStaff(request);
        return new ResponseEntity<>(staff, HttpStatus.CREATED);
    }

    @PutMapping("/update/{staffId}")
    public ResponseEntity<Staff> updateStaff(@PathVariable int staffId, @RequestBody CreateStaffRequest request) {
        Staff updatedStaff = staffService.updateStaff(staffId, request);
        return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{staffId}")
    public ResponseEntity<String> deleteStaff(@PathVariable int staffId) {
        staffService.deleteStaffById(staffId);
        return new ResponseEntity<>("Staff deleted successfully", HttpStatus.OK);
    }
}
