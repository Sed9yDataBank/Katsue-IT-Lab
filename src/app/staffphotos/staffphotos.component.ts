import { from } from 'rxjs';
import { StaffUploads } from './staff-uploads';
import { HttpClient} from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-staffphotos',
  templateUrl: './staffphotos.component.html',
  styleUrls: ['./staffphotos.component.css']
})
export class StaffphotosComponent implements OnInit {

  photosForm: FormGroup;
  staffUploads : StaffUploads[] = [];
  constructor(public fb: FormBuilder, private http: HttpClient) {
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
    var formData: any = new FormData();
    formData.append("staffFullName", this.photosForm.get('staffFullName').value);
    formData.append("staffPosition", this.photosForm.get('staffPosition').value);
    formData.append("image", this.photosForm.get('image').value);

    this.http.post('http://localhost:8080/uploadImage', formData).subscribe(
      (response) => console.log(response),
      (error) => console.log(error)
    )
  }

 
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
