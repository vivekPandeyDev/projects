import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  private baseUrl = 'http://localhost:3000';

  constructor(private _http: HttpClient) {}

  addEmployee(data: any): Observable<any> {
    console.log(data);
    return this._http.post(`${this.baseUrl}/employees`, data);
  }

  updateEmployee(empId: number, data: any): Observable<any> {
    return this._http.put(`${this.baseUrl}/employees/${empId}`, data);
  }

  getEmployeeList(): Observable<any> {
    return this._http.get(`${this.baseUrl}/employees`);
  }

  deleteEmployee(empId: number): Observable<any> {
    return this._http.delete(`${this.baseUrl}/employees/${empId}`);
  }
}
