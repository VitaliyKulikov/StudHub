import {InjectionToken} from '@angular/core';

export const LocalStorage = new InjectionToken('localStorage');

export class LocalStorageC {
  setItem(name, value) {

  }

  removeItem(name) {

  }

  getItem(name): string {
    return '';
  }
}
