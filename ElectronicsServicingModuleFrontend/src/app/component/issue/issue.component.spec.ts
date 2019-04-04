import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {IssueComponent} from './issue.component';
import {RouterTestingModule} from '@angular/router/testing';
import {HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import {FormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';

describe('IssueComponent', () => {
  let component: IssueComponent;
  let fixture: ComponentFixture<IssueComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [IssueComponent],
      imports: [
        RouterTestingModule,
        HttpClientTestingModule,
        BrowserModule,
        FormsModule
      ],
      providers: []
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IssueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
