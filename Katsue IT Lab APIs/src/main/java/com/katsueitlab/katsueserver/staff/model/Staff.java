package com.katsueitlab.katsueserver.staff.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "Staff_Info")
@Data
public class Staff extends AuditModel {

    @Id
    private UUID id;

    @Size(max = 100)
    private String staffFullName;
    @Size(max = 100)
    private String staffPosition;
    @Size(max = 100)

    //Staff Images
    private String imageName;
    @Size(max = 100)
    private String imagePath; // S3 key
    @Size(max = 100)
    private String imageType;
    @Size(max = 100)
    private String imageSize;

    //Getters And Setters Needed For Business Logic In Service Layer


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStaffFullName() {
        return staffFullName;
    }

    public void setStaffFullName(String staffFullName) {
        this.staffFullName = staffFullName;
    }

    public String getStaffPosition() {
        return staffPosition;
    }

    public void setStaffPosition(String staffPosition) {
        this.staffPosition = staffPosition;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Optional<String> getImagePath() {
        return Optional.ofNullable(imagePath);
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImageSize() {
        return imageSize;
    }

    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return Objects.equals(id, staff.id) &&
                Objects.equals(staffFullName, staff.staffFullName) &&
                Objects.equals(staffPosition, staff.staffPosition) &&
                Objects.equals(imageName, staff.imageName) &&
                Objects.equals(imagePath, staff.imagePath) &&
                Objects.equals(imageType, staff.imageType) &&
                Objects.equals(imageSize, staff.imageSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, staffFullName, staffPosition, imageName, imagePath, imageType, imageSize);
    }
}