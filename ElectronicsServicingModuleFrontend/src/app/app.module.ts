import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { EquipmentListComponent } from './component/equipment-list/equipment-list.component';
import {HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from './app-routing.module';
import { EquipmentDetailsComponent } from './component/equipment-details/equipment-details.component';
import {FormsModule} from '@angular/forms';
import {DateFormatPipe} from './utils/DateFormatPipe';
import { IssueComponent } from './component/issue/issue.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    EquipmentListComponent,
    EquipmentDetailsComponent,
    DateFormatPipe,
    IssueComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule.forRoot()
  ],
  entryComponents: [IssueComponent],
  providers: [],
  bootstrap: [AppComponent]

})
export class AppModule { }
