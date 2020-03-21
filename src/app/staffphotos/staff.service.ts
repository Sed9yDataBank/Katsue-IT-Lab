import { Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { StaffUploads } from './staff-uploads';
import { map, catchError, tap } from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class StaffService {

  private url : string = "http://localhost:8080/uploadStaff";
  private baseurl : string = "http://localhost:8080/uploads";
 

  constructor(private http: HttpClient) { }

  //POST
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

  //Get
  getUploads(): Observable<StaffUploads[]> {
    return this.http.get<StaffUploads[]>(this.baseurl).pipe(
      tap(staffUploads => console.log("Current Number of Staff: " + staffUploads.length)),
      catchError(this.handleError)
    );
  }

  //UPDATE
  updateStaff(staffUploads: StaffUploads): Observable<number> {
    let httpHeaders = new HttpHeaders({
        'Content-Type': 'application/json'
    });
    return this.http.put<StaffUploads>(this.url + "/" + staffUploads.staffId, staffUploads, {
        headers: httpHeaders,
        observe: 'response'
    }
    ).pipe(
        map(res => res.status),
        catchError(this.handleError)
    );
  }

  //DELETE
  deleteStaffById(staffId: string): Observable<number> {
    return this.http.delete<number>(this.url + "/" + staffId).pipe(
        tap(status => console.log("Current status: " + status)),
        catchError(this.handleError)
    );
  }

  private handleError(error: any) {
    console.error(error);
    return throwError(error);
  }


}
