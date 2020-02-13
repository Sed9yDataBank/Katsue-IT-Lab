import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StaffphotosService {

  private _url : string = "http://localhost:8080/uploadImage";
  constructor(private _http: HttpClient) { }
 
  createStaff(formData): Observable<any> {
    return this._http.post<any>(this._url, formData);
  }

}
