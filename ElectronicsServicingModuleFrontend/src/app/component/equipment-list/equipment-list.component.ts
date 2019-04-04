import {Component, OnInit} from '@angular/core';
import {Equipment} from '../../model/Equipment';
import {EquipmentService} from '../../service/equipment/equipment.service';
import {Issue} from '../../model/Issue';

@Component({
  selector: 'app-equipment-list',
  templateUrl: './equipment-list.component.html',
  styleUrls: ['./equipment-list.component.css']
})
export class EquipmentListComponent implements OnInit {

  equipments: Equipment[];
  columns: string[];
  pageSize: number;
  pageOffset: number;

  constructor(private equipmentService: EquipmentService) {
    this.columns = ['', 'name', 'category', 'issues', 'comments'];
    this.pageSize = 999; // TODO change this to page size, e.g. 10 after implementing paging
    this.pageOffset = 0;
    equipmentService.getEquipmentPage(this.pageOffset, this.pageSize).subscribe(equipments => this.equipments = equipments);
  }

  ngOnInit() {
  }

  hasPendingIssues(equipment: Equipment) {
    return equipment.issues.find(issue => issue.status === Issue.PENDING) !== undefined;
  }

}
