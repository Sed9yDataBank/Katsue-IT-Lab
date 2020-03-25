# Katsue IT Lab Web Application
>  Katsue IT Lab Is A Project Launched By Katsue Information Company, As A Business, Katsue Needs More "Persistent" Present Online.

## Table of contents
* [General info](#general-info)
* [Screenshots](#screenshots)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)
* [Inspiration](#inspiration)
* [Contact](#contact)

## General info
For Katsue IT Lab As A startup It is important For Customers To Know That They Can Reach Katsue Easily. Having This Information Online Helps Potential Customers Reach Katsue Offices.
With This Web Application Katsue IT Lab Have The Ability To Promote And Advertise The Startup In An Organic, Professional Manner. 
Katsue IT Lab Is Backed Up With A Functional Admin Panel, This Was Created In Order To Fight Back Struggles Of The Human Resource Department, Who Is Mainly Responsible About Adding, Changing Content In The Website.

We Made A Survery About Which Sections Are The Most Ones Changed Over The Year In The Website, And The Result Is, The Informative Section About Employees In Join Us Page, And The Way HR Manages Recieved Messages From The Contact Form In Home Page.

Before The HR Team Always Needed Assistance From The Tech Departmenet To Change Code Layout When Adding New Things, Which Is Frustrating Specially Styling The New Changes In Order To Not Lose Website Responsivity. Also It Is A Waste Of Time For The Devs Since They Have Other Main Tasks To Do, Specially That Katsue IT Lab Is New Start Up So Every Extra Effort Counts.

## Screenshots
![Katsue Home Page](https://github.com/Sed9yDataBank/Katsue-IT-Lab/blob/master/Katsue%20Screenshots/Home.gif)
![Katsue Join Us Page](https://github.com/Sed9yDataBank/Katsue-IT-Lab/blob/master/Katsue%20Screenshots/JoinUs.gif)
![Katsue Login Page](https://github.com/Sed9yDataBank/Katsue-IT-Lab/blob/master/Katsue%20Screenshots/LoginAuthentication.gif)
![Katsue Admin Panel/Manage Employees](https://github.com/Sed9yDataBank/Katsue-IT-Lab/blob/master/Katsue%20Screenshots/AddStaff.gif)
![Katsue Admin Panel/Mail Box](https://github.com/Sed9yDataBank/Katsue-IT-Lab/blob/master/Katsue%20Screenshots/MailBox.gif)


## Technologies

For Frontend
* Angular ( For Non-Technical Viewers Of This Project, HTML, CSS, JavaScript, TypeScript )
* Bootstrap
* NGX Bootstrap

For Backend
* SpringBoot ( For Non-Technical Viewers Of This Project, J2EE Java )
* JPA 
* Hibernate
* PostgreSQL
* Postman

For Project Managment
* Trello
* nTask

## Setup
Describe how to install / setup your local environement / add link to demo version.

## Code Examples
Business Logic In Service Layer Of Adding Staff Information To Join Us Section And Admin Panel And Locally Saving Employee Photo In Chosen Folder And Saving The Data In DataBase

 ```java
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
 ``` 

## Features

* Functional Admin Panel With Login Authentication, Content Managment And APIs Documentation
* Edit Team Members Section In Join Us Page Using Admin Panel
* Control Recieved Messages From Users And Send Replies With Your Gmail From The Admin Panel

To-Do List:
* Shift Project To Testing Environement
* Code A More Complex Login Authentication

## Status
Project is: _in progress_, Currentley In Testing, Quality Assurance Environment 

## Inspiration
Project Inspired By Dann Lenard, Katsue IT Lab CEO. Based On Building Online Presence For The Startup

## Contact
Created by [@Sed9yDataBank](https://github.com/Sed9yDataBank) - Feel Free To Contact Me ! [benzidsedki@gmail.com]
