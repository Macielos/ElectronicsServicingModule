import { Component, OnInit } from '@angular/core';
import {Equipment} from '../model/Equipment';
import {EquipmentService} from '../service/equipment.service';

@Component({
  selector: 'app-equipment-list',
  templateUrl: './equipment-list.component.html',
  styleUrls: ['./equipment-list.component.css']
})
export class EquipmentListComponent implements OnInit {

  equipments: Equipment[];
  columns: string[];

  constructor(private equipmentService: EquipmentService) {
    equipmentService.getEquipments().subscribe(equipments => this.equipments = equipments);
    this.columns = ['', 'name', 'category', 'issues', 'comments'];
  }

  ngOnInit() {
  }

  hasPendingIssues(equipment: Equipment) {
    return equipment.issues.find(issue => issue.status === 'ACTIVE') != null;
  }

}
