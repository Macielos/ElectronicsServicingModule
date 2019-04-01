import { Component, OnInit } from '@angular/core';
import {EquipmentService} from '../service/equipment.service';
import {Equipment} from '../model/Equipment';
import {ActivatedRoute} from '@angular/router';
import { Location } from '@angular/common';
import {Parameter} from '../model/Parameter';
import {Issue} from '../model/Issue';

@Component({
  selector: 'app-equipment-details',
  templateUrl: './equipment-details.component.html',
  styleUrls: ['./equipment-details.component.css']
})
export class EquipmentDetailsComponent implements OnInit {

  selectedEquipment: Equipment;
  categories: string[];

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private equipmentService: EquipmentService) {
    this.fetchEquipment();
    this.fetchCategories();
  }

  ngOnInit() {
  }

  private fetchEquipment() {
    const id = this.route.snapshot.paramMap.get('id');
    this.equipmentService.getEquipment(id).subscribe(equipment => this.selectedEquipment = equipment);
  }

  private fetchCategories() {
    this.categories = ['AGD', 'RTV', 'Computers'];
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.equipmentService.save(this.selectedEquipment);
    this.goBack();
  }

  addParameter() {
    this.selectedEquipment.parameters = this.selectedEquipment.parameters.concat(new Parameter());
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
}
