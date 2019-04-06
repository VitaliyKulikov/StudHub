export interface IUser {
  username: string;
  role: string;
}

export enum Role {
  Admin= 'Admin',
  Volunteer = 'Volunteer',
  Anonymous = 'Anonymous'
}
