import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ConfigService} from '../config/config.service';
import {Observable} from 'rxjs';
import {Issue} from '../../model/Issue';

@Injectable({
  providedIn: 'root'
})
export class IssueService {

  constructor(private httpClient: HttpClient,
              private configService: ConfigService) { }

  startOrUpdateIssue(issue: Issue, equipmentId: number): Observable<Issue> {
    return this.httpClient.post(this.configService.issueUrl + '?equipmentId=' + equipmentId, issue,
      this.configService.httpJsonOptions) as Observable<Issue>;
  }
}
