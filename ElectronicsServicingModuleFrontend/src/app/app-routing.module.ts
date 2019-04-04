import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {EquipmentListComponent} from './component/equipment-list/equipment-list.component';
import {EquipmentDetailsComponent} from './component/equipment-details/equipment-details.component';

const routes: Routes = [
  {path: '', redirectTo: '/equipment-list', pathMatch: 'full'},
  {path: 'equipment-list', component: EquipmentListComponent},
  {path: 'equipment-details/:id', component: EquipmentDetailsComponent}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

