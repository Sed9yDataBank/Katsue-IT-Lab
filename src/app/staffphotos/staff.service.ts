import { Injectable, ErrorHandler } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StaffUploads } from './staffUploadsModel';

@Injectable({
  providedIn: 'root'
})
export class StaffService {

  staffUploads : StaffUploads[];
  private url : string = "http://localhost:8080/uploadImage";
  private baseurl : string = "http://localhost:8080/uploads";

  constructor(private http: HttpClient) { }

  //POST
  createStaff(data): Observable<any> {
    return this.http.post<any>(this.url, data);
  }

  //Get
  getUploads(): Observable<StaffUploads[]> {
    return this.http.get<StaffUploads[]>(this.baseurl)
  }
}
