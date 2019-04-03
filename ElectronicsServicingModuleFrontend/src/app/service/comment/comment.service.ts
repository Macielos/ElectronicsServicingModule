import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {ConfigService} from '../config/config.service';
import {Comment} from '../../model/Comment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private httpClient: HttpClient,
              private configService: ConfigService) { }

  addComment(comment: Comment, equipmentId: number): Observable<Comment> {
    return this.httpClient.post(this.configService.commentUrl + '?equipmentId=' + equipmentId, comment,
      this.configService.httpJsonOptions) as Observable<Comment>;
  }
}
