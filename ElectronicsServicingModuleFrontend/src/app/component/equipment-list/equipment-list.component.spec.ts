import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {EquipmentListComponent} from './equipment-list.component';
import {RouterTestingModule} from '@angular/router/testing';
import {HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import {FormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';

describe('EquipmentListComponent', () => {
  let component: EquipmentListComponent;
  let fixture: ComponentFixture<EquipmentListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [EquipmentListComponent],
      imports: [
        RouterTestingModule,
        HttpClientTestingModule,
        BrowserModule,
        FormsModule
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EquipmentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
