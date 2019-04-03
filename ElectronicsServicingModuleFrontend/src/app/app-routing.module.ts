import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
// import {AuthGuard} from './security/auth.guard';
import {EquipmentListComponent} from './component/equipment-list/equipment-list.component';
import {EquipmentDetailsComponent} from './component/equipment-details/equipment-details.component';
import {IssueComponent} from './component/issue/issue.component';

const routes: Routes = [
  { path: '', redirectTo: '/equipment-list', pathMatch: 'full' },
  { path: 'equipment-list', component: EquipmentListComponent },
  { path: 'equipment-details/:id', component: EquipmentDetailsComponent }
//  { path: 'login', component: LoginComponent },
];

@NgModule({
  declarations: [],
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }

