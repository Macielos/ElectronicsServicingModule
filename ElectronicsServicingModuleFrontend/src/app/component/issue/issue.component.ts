import {Component, OnInit} from '@angular/core';
import {Issue} from '../../model/Issue';
import {Equipment} from '../../model/Equipment';
import {IssueService} from '../../service/issue/issue.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-issue',
  templateUrl: './issue.component.html',
  styleUrls: ['./issue.component.css']
})
export class IssueComponent implements OnInit {

  tmpIssue: Issue;
  selectedEquipment: Equipment;
  issueIndex: number;

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
    if (issueIndex >= 0) {
      Object.assign(this.tmpIssue, selectedEquipment.issues[issueIndex]);
    }
  }

  save() {
    let issueToSave: Issue;
    if (this.issueIndex >= 0) {
      issueToSave = Object.assign(this.selectedEquipment.issues[this.issueIndex], this.tmpIssue);
    } else {
      this.tmpIssue.creationDate = new Date();
      issueToSave = Object.assign({}, this.tmpIssue);
      this.selectedEquipment.issues.push(issueToSave);
    }

    this.issueService.startOrUpdateIssue(issueToSave, this.selectedEquipment.id).subscribe(result => {
      issueToSave.id = result.id;
      this.tmpIssue = null;
      this.modalService.dismissAll();
    });
  }
}
