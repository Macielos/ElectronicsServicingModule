import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Equipment} from '../../model/Equipment';
import {HttpClient} from '@angular/common/http';
import {ConfigService} from '../config/config.service';

@Injectable({
  providedIn: 'root'
})
export class EquipmentService {

  constructor(private httpClient: HttpClient,
              private configService: ConfigService) {
  }

  getEquipmentPage(offset: number, size: number): Observable<Equipment[]> {
    return this.httpClient
      .get(this.configService.equipmentUrl + '?offset=' + offset + '&size=' + size,
        this.configService.httpJsonOptions) as Observable<Equipment[]>;
  }

  getEquipment(id: string): Observable<Equipment> {
    return this.httpClient.get(this.configService.equipmentUrl + '?id=' + id, this.configService.httpJsonOptions) as Observable<Equipment>;
  }

  save(equipment: Equipment): Observable<Equipment> {
    return this.httpClient.post(this.configService.equipmentUrl, equipment, this.configService.httpJsonOptions) as Observable<Equipment>;
  }

  getCategories(): Observable<string[]> {
    return this.httpClient.get(this.configService.categoryUrl, this.configService.httpJsonOptions) as Observable<string[]>;
  }
}
