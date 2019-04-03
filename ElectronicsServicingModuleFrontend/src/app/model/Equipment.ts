import {Parameter} from './Parameter';
import {Issue} from './Issue';
import {Comment} from './Comment';

export class Equipment {
  id: number;
  name: string;
  category: string;
  parameters: Parameter[];
  issues: Issue[];
  comments: Comment[];

}
