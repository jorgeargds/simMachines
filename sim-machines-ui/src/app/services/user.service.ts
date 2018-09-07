import {Injectable } from '@angular/core';
import { HttpService} from './http.service';


@Injectable()
export class UserService {
  private _user;


  public get user() {
    return this._user;
  }

  public set user(value) {
    this._user = value;
  }


}
