import {Component, OnInit} from '@angular/core';
import {Issue} from '../../model/Issue';
import {Equipment} from '../../model/Equipment';
import {IssueService} from '../../service/issue/issue.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {isNullOrUndefined} from 'util';

@Component({
  selector: 'app-issue',
  templateUrl: './issue.component.html',
  styleUrls: ['./issue.component.css']
})
export class IssueComponent implements OnInit {

  tmpIssue: Issue;
  selectedEquipment: Equipment;
  issueIndex: number;
  errors: string[];

  issueStatuses: string[];

  constructor(private issueService: IssueService,
              private modalService: NgbModal) {
    this.issueStatuses = [ Issue.PENDING, Issue.RESOLVED ];
  }

  ngOnInit() {
  }

  init(selectedEquipment: Equipment, issueIndex: number) {
    console.log('Init issue ' + issueIndex);
    this.selectedEquipment = selectedEquipment;
    this.issueIndex = issueIndex;
    this.tmpIssue = new Issue();
    this.errors = [];
    if (issueIndex >= 0) {
      Object.assign(this.tmpIssue, selectedEquipment.issues[issueIndex]);
    }
  }

  save() {
    if (this.validateFields()) {
      let issueToSave: Issue;
      if (this.issueIndex >= 0) {
        issueToSave = Object.assign(this.selectedEquipment.issues[this.issueIndex], this.tmpIssue);
      } else {
        this.tmpIssue.creationDate = new Date();
        issueToSave = Object.assign({}, this.tmpIssue);
        this.selectedEquipment.issues.push(issueToSave);
      }

      if (this.selectedEquipment.id > 0) {
        this.issueService.startOrUpdateIssue(issueToSave, this.selectedEquipment.id)
          .subscribe(result => issueToSave.id = result.id, error => this.errors = ['Failed to save data on server']);
      }
      this.tmpIssue = null;
      this.modalService.dismissAll();
    }
  }

  private validateFields(): boolean {
    this.errors = [];
    if (this.isEmpty(this.tmpIssue.title)) {
      this.errors.push('Title cannot be empty');
    }
    if (isNullOrUndefined(this.tmpIssue.status)) {
      this.errors.push('Status cannot be empty');
    }
    return this.errors.length === 0;
  }

  private isEmpty(s: string): boolean {
    return isNullOrUndefined(s) || s.length === 0;
  }

}
