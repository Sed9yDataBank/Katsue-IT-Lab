package com.katsueitlab.katsueserver.staff.controller;

import com.katsueitlab.katsueserver.staff.model.Staff;
import com.katsueitlab.katsueserver.staff.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StaffController {

    //Other From Of Dependency Injection
    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping(value = "/uploads")
    public Page<Staff> getAllUploads(Pageable pageable) {
        return staffService.getAllUploads(pageable);
    }

    @PostMapping(value = "/uploadStaff",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadStaff(@Valid Staff image,
                                         @RequestParam("staffFullName") final String staffFullName,
                                         @RequestParam("staffPosition") final String staffPosition,
                                         @RequestParam("image")MultipartFile file ) throws IOException {

        staffService.uploadStaff(image, staffFullName, staffPosition, file);
    }

    @GetMapping("{staffId}/image/download")
    public byte[] downloadStaffImage(@PathVariable("userProfileId") UUID staffId) {
        return staffService.downloadStaffImage(staffId);
    }

    @PutMapping(value = "/uploadStaff/{staffId}")
    public Staff updateStaff(@PathVariable UUID staffId, @Valid @RequestBody Staff staffRequest) {
        return staffService.updateStaff(staffId, staffRequest);
    }

    @DeleteMapping(value = "/uploadStaff/{staffId}")
    public ResponseEntity<?> deleteStaff(@PathVariable UUID staffId) {
        return staffService.deleteStaff(staffId);
    }
}