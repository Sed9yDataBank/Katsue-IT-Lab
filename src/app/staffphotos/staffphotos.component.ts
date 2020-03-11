import { from } from 'rxjs';
<<<<<<< HEAD
import {StaffUploads } from './staffUploadsModel';
import { HttpClient} from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { StaffService } from './staff.service';
=======
import { StaffUploads } from './staff-uploads';
import { HttpClient} from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
>>>>>>> d1eba28c6f70036f19cfd08661430c90883805b5

@Component({
  selector: 'app-staffphotos',
  templateUrl: './staffphotos.component.html',
  styleUrls: ['./staffphotos.component.css']
})
export class StaffphotosComponent implements OnInit {

  photosForm: FormGroup;
<<<<<<< HEAD
  constructor(public fb: FormBuilder, private baseservice: StaffService) {
=======
  staffUploads : StaffUploads[] = [];
  constructor(public fb: FormBuilder, private http: HttpClient) {
>>>>>>> d1eba28c6f70036f19cfd08661430c90883805b5
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
<<<<<<< HEAD
    let formData: any = new FormData();
=======
    var formData: any = new FormData();
>>>>>>> d1eba28c6f70036f19cfd08661430c90883805b5
    formData.append("staffFullName", this.photosForm.get('staffFullName').value);
    formData.append("staffPosition", this.photosForm.get('staffPosition').value);
    formData.append("image", this.photosForm.get('image').value);

<<<<<<< HEAD
    this.baseservice.createStaff(formData).subscribe(
=======
    this.http.post('http://localhost:8080/uploadImage', formData).subscribe(
>>>>>>> d1eba28c6f70036f19cfd08661430c90883805b5
      (response) => console.log(response),
      (error) => console.log(error)
    )
  }

<<<<<<< HEAD

  getAllStaff() {
    return this.baseservice.getUploads().subscribe((res) => {
      this.baseservice.staffUploads = res as StaffUploads[];
      console.log("Something Is Wrong ----->", res);
    })
  }
}
 
=======
 
  public getAllStaff() {
    let url = "http://localhost:8080/uploads";
    this.http.get<StaffUploads[]>(url).subscribe(
      res => {
        this.staffUploads = res;
        console.log("Staffffffffffffffffffffff")
        console.log(res)
      },
      err => {
        alert("An Error Has Occured ! ")
      }
    );
  }


}
>>>>>>> d1eba28c6f70036f19cfd08661430c90883805b5
