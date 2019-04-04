import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {EquipmentDetailsComponent} from './equipment-details.component';
import {RouterTestingModule} from '@angular/router/testing';
import {HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import {FormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';

describe('EquipmentDetailsComponent', () => {
  let component: EquipmentDetailsComponent;
  let fixture: ComponentFixture<EquipmentDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [EquipmentDetailsComponent],
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
    fixture = TestBed.createComponent(EquipmentDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
