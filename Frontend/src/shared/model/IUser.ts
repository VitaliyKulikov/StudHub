export interface IUser {
  username: string;
  role: string;
  id: number;
}

export interface IUserToken {
  exp: number;
  iat: number;
  role: string;
  iss: string;
  sub: number;
}

export enum Role {
  Admin = 'ROLE_ADMIN',
  Volunteer = 'ROLE_VOLUNTEER',
  Organisation = 'ROLE_ORGANISATION',
  Anonymous = 'Anonymous'
}
