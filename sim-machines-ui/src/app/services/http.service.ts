import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';

import {map} from 'rxjs/operators';
import {LoadingBarService} from '@ngx-loading-bar/core';

@Injectable()
export class HttpService {
  static server = 'http://localhost:8080';
  requestInQueue = [];


  constructor(private http: Http, private loadingBar: LoadingBarService) {
  }

  get(url): any {
    this.loadingBar.start();
    this.checkRequestQueue(url, 'add');
    return this.http.get(HttpService.server + url)
      .pipe(map((res: Response) => {
        this.checkRequestQueue(url, 'remove');
          return res.json();
        }
      ));
  }

  post(url, body): any {
    this.loadingBar.start();
    return this.http.post(HttpService.server + url, body)
      .pipe(map((res: Response) => res.json()));
  }

  checkRequestQueue(url, action) {
    if (action === 'add') {
      this.requestInQueue.push(url);
    } else {
      this.requestInQueue.splice(this.requestInQueue.indexOf(url), 1);
    }
    if (this.requestInQueue.length === 0) {
      this.loadingBar.stop();
    }
  }


}
