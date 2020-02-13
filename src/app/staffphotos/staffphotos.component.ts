import { StaffphotosService } from './staffphotos.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import {ImageModal} from './imageModal'
@Component({
  selector: 'app-staffphotos',
  templateUrl: './staffphotos.component.html',
  styleUrls: ['./staffphotos.component.css']
})
export class StaffphotosComponent implements OnInit {

  imageObject: ImageModal;
  photosForm: FormGroup;
  
  constructor(private formBuilder: FormBuilder, private service:StaffphotosService) { }

  ngOnInit() {
    this.photosForm = this.formBuilder.group({
      staffFullName: [''],
      staffPosition: [''],
      image: ['']
    });
  }
  
  sendImage(form:FormGroup) {

    this.imageObject = new ImageModal()
    this.imageObject.staffFullName = this.photosForm.value["staffFullName"];
    this.imageObject.staffPosition = this.photosForm.value["staffPosition"];
    this.imageObject.image = this.photosForm.value["image"];

    this.service.createStaff(this.imageObject).subscribe(data=> {

    })

  }

}
