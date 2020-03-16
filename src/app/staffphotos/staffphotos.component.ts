import { from } from 'rxjs';
import {StaffUploads } from './staffUploadsModel';
import { HttpClient} from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { StaffService } from './staff.service';

@Component({
  selector: 'app-staffphotos',
  templateUrl: './staffphotos.component.html',
  styleUrls: ['./staffphotos.component.css']
})
export class StaffphotosComponent implements OnInit {

  photosForm: FormGroup;
  constructor(public fb: FormBuilder, private baseservice: StaffService) {
    this.photosForm = this.fb.group({
      staffFullName: [''],
      staffPosition: [''],
      image: [null]
    })
   }

  ngOnInit() { 
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

    this.baseservice.createStaff(formData).subscribe(
      (response) => console.log(response),
      (error) => console.log(error)
    )
  }


  getAllStaff() {
    return this.baseservice.getUploads().subscribe((res) => {
      this.baseservice.staffUploads = res as StaffUploads[];
      console.log("Something Is Wrong ----->", res);
    })
  }
}
 
