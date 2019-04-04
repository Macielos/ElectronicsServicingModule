import {Component, OnInit} from '@angular/core';
import {EquipmentService} from '../../service/equipment/equipment.service';
import {Equipment} from '../../model/Equipment';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {Parameter} from '../../model/Parameter';
import {Issue} from '../../model/Issue';
import {DateFormatPipe} from '../../utils/DateFormatPipe';
import {Comment} from '../../model/Comment';
import {IssueService} from '../../service/issue/issue.service';
import {CommentService} from '../../service/comment/comment.service';
import {IssueComponent} from '../issue/issue.component';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {isNullOrUndefined} from 'util';

@Component({
  selector: 'app-equipment-details',
  templateUrl: './equipment-details.component.html',
  styleUrls: ['./equipment-details.component.css']
})
export class EquipmentDetailsComponent implements OnInit {

  selectedEquipment: Equipment;
  categories: string[];
  newComment: Comment;
  issueColumns: string[];
  errors: string[];

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private equipmentService: EquipmentService,
    private issueService: IssueService,
    private commentService: CommentService,
    private dateFormatPipe: DateFormatPipe,
    private modalService: NgbModal
  ) {
    this.fetchCategories();
    this.fetchEquipment();
    this.issueColumns = ['title', 'status', 'creation date'];
    this.errors = [];
  }

  ngOnInit() {
  }

  private fetchEquipment() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id !== '0') {
      this.equipmentService.getEquipment(id).subscribe(equipment => this.selectedEquipment = equipment);
    } else {
      this.selectedEquipment = new Equipment();
      this.selectedEquipment.parameters = [];
      this.selectedEquipment.issues = [];
      this.selectedEquipment.comments = [];
    }
  }

  private fetchCategories() {
    this.equipmentService.getCategories().subscribe(categories => this.categories = categories);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.validateFields()) {
      this.equipmentService.save(this.selectedEquipment)
        .subscribe(() => this.goBack(), error => this.errors = ['Failed to save data on server']);
    }
  }

  private validateFields(): boolean {
    this.errors = [];
    if (this.isEmpty(this.selectedEquipment.name)) {
      this.errors.push('Name cannot be empty');
    }
    if (this.isEmpty(this.selectedEquipment.category)) {
      this.errors.push('Category cannot be empty');
    }
    if (this.selectedEquipment.parameters.find(param => this.isEmpty(param.name) || this.isEmpty(param.value)) !== undefined) {
      this.errors.push('Parameter cannot be empty');
    }
    return this.errors.length === 0;
  }

  private isEmpty(s: string): boolean {
    return isNullOrUndefined(s) || s.length === 0;
  }

  addParameter() {
    if (isNullOrUndefined(this.selectedEquipment.parameters)) {
      this.selectedEquipment.parameters = [];
    }
    this.selectedEquipment.parameters.push(new Parameter());
  }

  deleteParameter(index: number) {
    this.selectedEquipment.parameters.splice(index, 1);
  }

  isPending(issue: Issue) {
    return issue.status === Issue.PENDING;
  }

  isResolved(issue: Issue) {
    return issue.status === Issue.RESOLVED;
  }

  formatDate(date: Date) {
    return this.dateFormatPipe.transform(date);
  }

  addComment() {
    this.newComment = new Comment();
  }

  sendComment() {
    if (this.newComment.comment.length > 0) {
      this.newComment.author = 'Test User';
      this.newComment.publicationDate = new Date();
      this.selectedEquipment.comments.push(this.newComment);
      const comment = this.newComment;
      if (this.selectedEquipment.id > 0) {
        this.commentService.addComment(comment, this.selectedEquipment.id)
          .subscribe(result => comment.id = result.id, error => this.errors = ['Failed to save data on server']);
      }
      this.newComment = null;
    }
  }

  startNewIssue() {
    this.editIssue(-1);
  }

  editIssue(issueIndex: number) {
    const openedComponent = this.modalService.open(IssueComponent, {ariaLabelledBy: 'modal-basic-title', size: 'lg'});
    openedComponent.componentInstance.init(this.selectedEquipment, issueIndex);
  }

}
