import { Routes } from '@angular/router';
import { AdminPageComponent } from './components/admin-page/admin-page.component';
import { EmployeePageComponent } from './components/employee-page/employee-page.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { FilteredEmployeesComponent } from './components/filtered-employees/filtered-employees.component';

export const routes: Routes = [
    
    { path:'homepage', component:HomePageComponent},
    // { path:'mainpage' , component:AdminPageComponent},
    { path:'admin', component:AdminPageComponent},
    { path:'employee', component:EmployeePageComponent},
    { path:'employee-list', component:FilteredEmployeesComponent},
    { path:'',redirectTo: 'admin', pathMatch: 'full'},
    { path: '**', redirectTo: 'admin' }
];
