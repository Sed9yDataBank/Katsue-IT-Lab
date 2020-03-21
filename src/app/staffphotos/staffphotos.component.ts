import {StaffUploads } from './staff-uploads';
import { FormBuilder, FormGroup} from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { StaffService } from './staff.service';

@Component({
  selector: 'app-staffphotos',
  templateUrl: './staffphotos.component.html',
  styleUrls: ['./staffphotos.component.css']
})
export class StaffphotosComponent implements OnInit {

  photosForm: FormGroup;
  allStaffUploads : StaffUploads[];
  statusCode: number;

  constructor(public fb: FormBuilder, private baseservice: StaffService) {
    this.photosForm = this.fb.group({
      staffFullName: [''],
      staffPosition: [''],
      image: [null]
    })
   }

  ngOnInit() :void{ 
    this.getAllStaff();
  }
  
  uploadFile(event) {
    const file  = (event.target as HTMLInputElement).files[0];
    this.photosForm.patchValue({
      image: file
    });
    this.photosForm.get('image').updateValueAndValidity()
  }

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


  getAllStaff() {
		this.baseservice.getUploads().subscribe(
				data => this.allStaffUploads = data,
        errorCode => this.statusCode = errorCode
      );
  }
  
  updateStaffById(staffUploads) {
    this.baseservice.updateStaff(staffUploads).subscribe(statusCode => {
      this.statusCode = 200;
      this.getAllStaff();
      },
      errorCode => this.statusCode = errorCode
    );
  }

  deleteStaff(staffId: string) {
		this.preProcessConfigurations();
		this.baseservice.deleteStaffById(staffId)
			.subscribe(successCode => {
				//this.statusCode = successCode;
				//Expecting success code 204 from server
				this.statusCode = 204;
				this.getAllStaff();
			},
				errorCode => this.statusCode = errorCode);
	}
  
  //Perform preliminary configurations
	preProcessConfigurations() {
		this.statusCode = null;
	}
}
 
