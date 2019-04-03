import { Injectable } from '@angular/core';
import {HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  url = 'http://localhost:8100';
  equipmentUrl = this.url + '/equipment';
  categoryUrl = this.url + '/category';
  issueUrl = this.url + '/issue';
  commentUrl = this.url + '/comment';

  httpJsonOptions = {
    headers: new HttpHeaders({
      'Access-Control-Allow-Origin': '*',
      'Authorization': 'Basic ' + btoa('user:pass'),
      'Content-Type':  'application/json'
    })
  };

  constructor() { }


}
