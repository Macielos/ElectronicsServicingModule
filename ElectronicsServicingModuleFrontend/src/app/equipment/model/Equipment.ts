import {Parameter} from './Parameter';
import {Issue} from './Issue';
import {Comment} from './Comment';

export class Equipment {
  id: string;
  name: string;
  category: string;
  parameters: Parameter[];
  issues: Issue[];
  comments: Comment[];
}
