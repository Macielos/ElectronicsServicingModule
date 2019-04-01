import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {Equipment} from '../model/Equipment';

@Injectable({
  providedIn: 'root'
})
export class EquipmentService {

   equipments: Equipment[];

  constructor() {
    this.equipments = [
      {id: '1', name: 'Pralka Bosch', category: 'AGD', parameters: [{name: 'a', value: 'b'}, { name: 'c', value: 'd'}],
        issues: [
          {title: 'zjebalo sie', description: 'no zjebalo sie i huj', status: 'RESOLVED', creationDate: new Date()},
          {title: 'znowu sie zjebalo', description: 'no zjebalo sie i huj', status: 'PENDING', creationDate: new Date()}
          ], comments: []},
      {id: '2', name: 'ASUS', category: 'Computers', parameters: [{name: 'x', value: 'y'}],
        issues: [], comments: [
          { author: 'janusz',
            comment: 'kurła nie dzialaja te komputry cale, kiedys to bylo kurła, oddajcie mie moje piniadze kurła',
            publicationDate: new Date()},
          { author: 'grazyna',
            comment: 'pisze zeby wlaczyc to wlanczam i nic sie nie dzieje',
            publicationDate: new Date()}
        ]}
    ];
  }

  getEquipments(): Observable<Equipment[]> {
    return of(this.equipments);
  }

  getEquipment(equipmentId: string): Observable<Equipment> {
    return of(this.equipments.find(equipment => equipment.id === equipmentId));
  }

  save(equipment: Equipment) {

  }
}
