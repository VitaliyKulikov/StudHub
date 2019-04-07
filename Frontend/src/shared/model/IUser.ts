export interface IUser {
  username: string;
  role: string;
}

export interface IUserToken {
  exp: number;
  iat: number;
  role: string;
  iss: string;
}

export enum Role {
  Admin = 'ROLE_ADMIN',
  Volunteer = 'ROLE_VOLUNTEER',
  Anonymous = 'Anonymous'
}
