import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {User} from './user';
import {catchError} from 'rxjs/operators';


const baseURL = 'http://localhost:8080/api/v1/users/';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private httpClient: HttpClient) {
  }

  getAll(): Observable<User[]> {

    return this.httpClient.get<User[]>(baseURL )
      .pipe(
        catchError(this.errorHandler)
      );
  }

  create(user): Observable<User> {
    return this.httpClient.post<User>(baseURL , JSON.stringify(user), this.httpOptions)
      .pipe(
        catchError(this.errorHandler)
      );
  }

  find(id): Observable<User> {
    return this.httpClient.get<User>(baseURL  + id)
      .pipe(
        catchError(this.errorHandler)
      );
  }

  update(id, user): Observable<User> {
    return this.httpClient.put<User>(baseURL  + id, JSON.stringify(user), this.httpOptions)
      .pipe(
        catchError(this.errorHandler)
      );
  }

  // tslint:disable-next-line:typedef
  delete(id) {
    return this.httpClient.delete<User>(baseURL  + id, this.httpOptions)
      .pipe(
        catchError(this.errorHandler)
      );
  }


  // tslint:disable-next-line:typedef
  errorHandler(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(errorMessage);
  }

}
