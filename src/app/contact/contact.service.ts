import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  private _url : string = "http://localhost:8080/senders";

  constructor(private _http: HttpClient) { }

  createMessage(sender):Observable<any> {
    return this._http.post<any>(this._url, sender);
  }
}
