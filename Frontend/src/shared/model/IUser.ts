export interface IUser {
  username: string;
  role: string;
}

export interface IUserToken extends IUser {
  exp: number;
  iat: number;
}

export enum Role {
  Admin = 'Admin',
  Volunteer = 'Volunteer',
  Anonymous = 'Anonymous'
}
