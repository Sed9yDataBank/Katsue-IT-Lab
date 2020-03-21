import { Injectable } from '@angular/core';
import { Mailbox } from './mailbox';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MailService {

  private path : string = "http://localhost:8080/messages";

  constructor(private http: HttpClient) { }

  getMessages(): Observable<Mailbox[]> {
    return this.http.get<Mailbox[]>(this.path).pipe(
      tap(mailBox => console.log("Number of Current Messages: " + mailBox.length)),
      catchError(this.handleError)
    );
  }

  public deleteMessage(id: string): Observable<number> {
    return this.http.delete<number>(this.path + "/" + id).pipe(
      tap(status => console.log("Current status: " + status)),
      catchError(this.handleError)
    );
  }
  
  private handleError(error: any) {
    console.error(error);
    return throwError(error);
  }

}

