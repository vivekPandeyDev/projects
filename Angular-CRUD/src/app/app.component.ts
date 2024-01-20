import { Component,Injectable,ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { EmpAddEditComponent } from './emp-add-edit/emp-add-edit.component';
import { EmployeeService } from './services/employee.service';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import {MatSort, MatSortModule} from '@angular/material/sort';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatFormFieldModule} from '@angular/material/form-field';
import { CoreService } from './core/core.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {

  displayedColumns: string[] = ['empId',
  'firstName', 
  'lastName', 
  'email',
   'dob',
   'gender',
   'education',
   'company',
   'experience',
   'emppackage',
   'action',
  ];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private _dialog: MatDialog,
     private _empService: EmployeeService,
     private _coreService: CoreService
     ){}

  ngOnInit(): void {
    this.getEmployeeList();
  }

  openAddEditEmpForm()
  {
    const dialogRef=this._dialog.open(EmpAddEditComponent);
    dialogRef.afterClosed().subscribe({
    next:(val)=>{
    if(val){
      this.getEmployeeList();
    }
  },
  });
  }

  getEmployeeList()
  {
    this._empService.getEmployeeList().subscribe({
      next: (res) => {
        console.log(res);
        this.dataSource=new MatTableDataSource(res);
        this.dataSource.sort=this.sort;
        this.dataSource.paginator=this.paginator;
      },
      error: console.log,
    });
  }


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  deleteEmployee(empId:number)
  {
    this._empService.deleteEmployee(empId).subscribe({
      next:(res)=>
      {
        this._coreService.openSnackBar('Employee Deleted','done')
        this.getEmployeeList();
      },
      error: console.log,

    })
  }

  openEditEmpForm(data: any)
  {
    const dialogRef= this._dialog.open(EmpAddEditComponent,{
         data,
    });
    dialogRef.afterClosed().subscribe({
      next:(val)=>{
      if(val){
        this.getEmployeeList();
      }
    },
    });

  }
}
