export class Issue {
  static PENDING = 'PENDING';
  static RESOLVED = 'RESOLVED';

  title: string;
  description: string;
  status: string;
  creationDate: Date;
}
