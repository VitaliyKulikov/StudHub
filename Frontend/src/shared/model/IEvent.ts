export interface IEvent {
  name: string;
  startDate: string;
  endDate: string;
  description: string;
  location: string;
  owner: {id:number, name:string};
}
