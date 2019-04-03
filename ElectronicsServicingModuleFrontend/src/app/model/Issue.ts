export class Issue {
  static PENDING = 'PENDING';
  static RESOLVED = 'RESOLVED';

  id: number;
  title: string;
  description: string;
  status: string;
  creationDate: Date;
}
