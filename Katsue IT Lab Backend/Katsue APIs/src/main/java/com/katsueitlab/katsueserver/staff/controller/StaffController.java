package com.katsueitlab.katsueserver.staff.controller;

import com.katsueitlab.katsueserver.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping(value = "/uploads")
    public Page<Staff> getAllUploads(Pageable pageable) {
        return staffService.getAllUploads(pageable);
    }

    @PostMapping(value = "/uploadStaff")
    public ResponseEntity<?> uploadStaff(@Valid Staff image,
                                         @RequestParam("staffFullName") final String staffFullName,
                                         @RequestParam("staffPosition") final String staffPosition,
                                         @RequestParam("image")MultipartFile file ) throws IOException {

        return staffService.uploadStaff(image, staffFullName, staffPosition, file);
    }

    @PutMapping(value = "/uploadStaff/{staffId}")
    public Staff updateStaff(@PathVariable Long staffId, @Valid @RequestBody Staff staffRequest) {
        return staffService.updateStaff(staffId, staffRequest);
    }

    @DeleteMapping(value = "/uploadStaff/{staffId}")
    public ResponseEntity<?> deleteStaff(@PathVariable Long staffId) {
        return staffService.deleteStaff(staffId);
    }
}
