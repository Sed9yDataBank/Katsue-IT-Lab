# Katsue IT Lab Web Application
>  Katsue It Lab is a project launched for Katsue company specialized in information technology, as a business company, Katsue needed more "persistent" present online.

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
For Katsue IT Lab as a startup it is important for customers to know that they can reach Katsue easily. Having this information online helps potential customers to reach Katsue offices.
With this web application Katsue It Lab have the ability to promote and advertise the startup in an fast, professional manner. 
The company website is backed up with a functional admin panel, this was created in order to ease struggles of the human resource department, who is mainly responsible for adding, changing content in the website, interacting with customers, potential employees and random visitors questions.

Before starting this project the HR team always needed assistance from the tech departmenet when updating content in the company website by digging in the source code., which is frustrating specially styling the new changes in order to not lose website responsivity. Also it is a waste of time for the developers since they already have  other main tasks to do, specially that Katsue is new start up so every extra minute of work counts.

With this web application hr team will be able to save appromoxatily 2 hours a day, lower their frustration and increasing everyone concentration on the startup products. Also audience and Ktasue global popilarity will peak to 28% by the end of the year, and could increase more when using a good digital marketing plan.
## Screenshots

![Katsue Home Page](https://github.com/Sed9yDataBank/Katsue-IT-Lab/blob/master/Katsue%20Screenshots/Home.gif)

![Katsue Join Us Page](https://github.com/Sed9yDataBank/Katsue-IT-Lab/blob/master/Katsue%20Screenshots/JoinUs.gif)

![Katsue Login Page](https://github.com/Sed9yDataBank/Katsue-IT-Lab/blob/master/Katsue%20Screenshots/LoginAuthentication.gif)

![Katsue Admin Panel/Manage Employees](https://github.com/Sed9yDataBank/Katsue-IT-Lab/blob/master/Katsue%20Screenshots/AddStaff.gif)

![Katsue Admin Panel/Mail Box](https://github.com/Sed9yDataBank/Katsue-IT-Lab/blob/master/Katsue%20Screenshots/MailBox.gif)


## Technologies

For Frontend
* Angular 
* Bootstrap
* NGX Bootstrap

For Backend
* SpringBoot
* JPA 
* Hibernate
* PostgreSQL
* Postman
* AWS
* SendGrid API

For Project Managment
* Trello
* nTask

## Setup

You can clone this repository and setup the frontend, server and provide your own postgres credentials, aws and sendgrid api tokens.

Why no live demo ? deploying this project is going to cost money for a good hosting service.

## Code Examples
Business logic in service layer of adding staff information to join us section and admin panel, we save the pictures in buckets using AWS and save every photo url in our database.

 ```java

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
 ``` 

```typescript
        //Staff Service
        createStaff(staffUploads: StaffUploads): Observable<number> {
            let httpHeaders = new HttpHeaders({
              'Content-Type': 'application/json'
            });
            return this.http.post<StaffUploads>(this.url, staffUploads, {
                    headers: httpHeaders,
                    observe: 'response'
                }
                ).pipe(
                  map(res => res.status),
                  catchError(this.handleError)
              );
            }
      
        //Staff Componenet 
            submitForm() {
            console.log(this.photosForm.value)
            let formData: any = new FormData();
            formData.append("staffFullName", this.photosForm.get('staffFullName').value);
            formData.append("staffPosition", this.photosForm.get('staffPosition').value);
            formData.append("image", this.photosForm.get('image').value);

            this.baseservice.createStaff(formData).subscribe(response => {
              console.log(response);
              this.getAllStaff();
            } ,
              error => console.log(error)
            );
          }
 ```
## Features

* Functional admin panel with login authentication providing custom ip authentication provider, content managment across the web application and apis documentation.
* edit team members section in join us page using admin panel.
* Control recieved messages from users and send replies with your gmail from the admin panel using SendGrid services.

To-Do List:
* Shift project to testing environement
* Code a more complex login authentication

## Status
Project is: _in progress_, Currentley In Testing, Quality Assurance Environment 

## Inspiration
Project Inspired By Dann Lenard, Katsue IT Lab CEO. Based On Building Online Presence For The Startup

## Contact
Created by [@Sed9yDataBank](https://github.com/Sed9yDataBank) - Feel Free To Contact Me : [ benzidsedki@gmail.com ]
