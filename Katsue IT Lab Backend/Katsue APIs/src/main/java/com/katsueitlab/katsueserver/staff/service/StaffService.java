package com.katsueitlab.katsueserver.staff.service;

import com.katsueitlab.katsueserver.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public Page<Staff> getAllUploads(Pageable pageable) {
        return staffRepository.findAll(pageable);
    }

    public ResponseEntity<?> uploadStaff(Staff staff, String staffFullName,
                                         String staffPosition,
                                         MultipartFile file) throws IOException {

        String imageName = file.getOriginalFilename();

        //Save Image Locally In Project Folder And Change Path ---->
        File saveImage = new File("/home/fianchetto/Documents/Katsue IT Lab/src/assets/TeamPhotos/" + imageName);
        saveImage.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(saveImage);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();

        String imagePath = Paths.get("/home/fianchetto/Documents/Katsue IT Lab/src/assets/TeamPhotos" + imageName)
                .toString();
        String imageType = file.getContentType();

        long size = file.getSize();
        String imageSize = String.valueOf(size);

        //Lombok Error From Here When Setting And Getting
        staff.setStaffFullName(staffFullName);
        staff.setStaffPosition(staffPosition);
        staff.setImageName(imageName);
        staff.setImagePath(imagePath);
        staff.setImageType(imageType);
        staff.setImageSize(imageSize);

        if (staff != null) {
            staffRepository.save(staff);
        }
        return new ResponseEntity<>("Staff Has Been Created Successfully", HttpStatus.OK);
    }

    public Staff updateStaff(Long staffId, Staff staffRequest) {
        return staffRepository.findById(staffId).map(image -> {
            image.setStaffFullName(staffRequest.getStaffFullName());
            image.setStaffPosition(staffRequest.getStaffPosition());
            return staffRepository.save(image);
        }).orElseThrow(() -> new ResourceNotFound("Staff Id " + staffId + " not found"));
    }

    public ResponseEntity<?> deleteStaff(Long staffId) {
        return staffRepository.findById(staffId).map(image -> {
            staffRepository.delete(image);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFound("Staff Id " + staffId + " not found"));
    }
}
