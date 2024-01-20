import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EmployeeService } from '../services/employee.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Inject } from '@angular/core';
import { Injectable } from '@angular/core';
import { CoreService } from '../core/core.service';

@Component({
  selector: 'app-emp-add-edit',
  templateUrl: './emp-add-edit.component.html',
  styleUrls: ['./emp-add-edit.component.css'],
})
export class EmpAddEditComponent {
  empForm: FormGroup;
  today = new Date();

  education: string[] = [
    'Matric',
    'Diploma',
    'Intermediate',
    'Graduate',
    'PostGraduate',
  ];

  constructor(
    private _fb: FormBuilder,
    private _empService: EmployeeService,
    private _dialogRef: MatDialogRef<EmpAddEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private _coreService: CoreService
  ) {
    this.empForm = this._fb.group({
      firstName: ['', Validators.required],
      lastName: [''],
      email: ['', Validators.required],
      dob: ['', Validators.required],
      gender: ['', Validators.required],
      education: '',
      company: ['', Validators.required],
      experience: ['', Validators.required],
      emppackage: '',
    });
  }

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.empForm.patchValue(this.data);
  }

  onFormSubmit() {
    if (this.empForm.valid) {
      if (this.data) {
        this._empService
          .updateEmployee(this.data.id, this.empForm.value)
          .subscribe({
            next: (val: any) => {
              this._coreService.openSnackBar(
                'Employee updated successfully',
                'done'
              );
              this._dialogRef.close(true);
            },
            error: (err: any) => {
              this._coreService.openSnackBar('Something went wrong', 'error');
              console.error(err);
            },
          });
      } else {
        this._empService.addEmployee(this.empForm.value).subscribe({
          next: (val: any) => {
            this._coreService.openSnackBar(
              'Employee added successfully',
              'done'
            );
            this._dialogRef.close(true);
          },
          error: (err: any) => {
            this._coreService.openSnackBar('Something went wrong', 'error');
            console.log(err);
          },
        });
      }
    } else {
      console.log('invalid form');
    }
  }

  hasError(controlName: string, errorName: string) {
    return this.empForm.get(controlName)?.hasError(errorName);
  }
}
