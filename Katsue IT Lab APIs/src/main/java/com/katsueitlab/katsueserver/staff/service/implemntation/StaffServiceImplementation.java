package com.katsueitlab.katsueserver.staff.service.implemntation;

import com.katsueitlab.katsueserver.staff.Bucket.BucketName;
import com.katsueitlab.katsueserver.staff.DTO.StaffListDTO;
import com.katsueitlab.katsueserver.staff.FileStore.FileStore;
import com.katsueitlab.katsueserver.staff.exception.ResourceNotFound;
import com.katsueitlab.katsueserver.staff.model.Staff;
import com.katsueitlab.katsueserver.staff.repository.StaffRepository;
import com.katsueitlab.katsueserver.staff.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
@RequiredArgsConstructor
public class StaffServiceImplementation implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private FileStore fileStore;

    @Override
    @Transactional
    public Page<Staff> getAllUploads(Pageable pageable) {
        return staffRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void uploadStaff(Staff user,
                            String staffFullName,
                            String staffPosition,
                            MultipartFile file) {
        // 1. Check if image is not empty
        isFileEmpty(file);
        // 2. If file is an image
        isImage(file);

        // 3. The user exists in our database
        //Staff user = getStaffImageOrThrow(id);

        // 4. Grab some metadata from file if any
        Map<String, String> metadata = extractMetadata(file);

        // 5. Store the image in s3 and update database (userProfileImageLink) with s3 image link
        String path = String.format("%s/%s", BucketName.STAFF_FACE.getBucketName(), user.getId());
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
            user.setImagePath(path);
            user.setStaffFullName(staffFullName);
            user.setStaffPosition(staffPosition);
            if (user != null) {
                staffRepository.save(user);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    @Transactional
    public byte[] downloadStaffImage(UUID id) {
        Staff user = getStaffImageOrThrow(id);

        String path = String.format("%s/%s",
                BucketName.STAFF_FACE.getBucketName(),
                user.getId());

        return user.getImagePath()
                .map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);

    }

    private Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private Staff getStaffImageOrThrow(UUID id) {
        StaffListDTO staffListDTO = new StaffListDTO();
        return StaffListDTO
                .getStaffList()
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Staff %s not found", id)));
    }

    private void isImage(MultipartFile file) {
        if (!Arrays.asList(
                IMAGE_JPEG.getMimeType(),
                IMAGE_PNG.getMimeType(),
                IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image [" + file.getContentType() + "]");
        }
    }

    private void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + "]");
        }
    }

    @Override
    @Transactional
    public Staff updateStaff(UUID staffId, Staff staffRequest) {
        return staffRepository.findById(staffId).map(user -> {
            user.setStaffFullName(staffRequest.getStaffFullName());
            user.setStaffPosition(staffRequest.getStaffPosition());
            return staffRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFound("Staff Id " + staffId + " not found"));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteStaff(UUID staffId) {
        return staffRepository.findById(staffId).map(user -> {
            staffRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFound("Staff Id " + staffId + " not found"));
    }
}
