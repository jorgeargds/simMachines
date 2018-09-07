import {Component, Input, OnInit} from '@angular/core';
import {HttpService} from '../../services/http.service';
import {UserService} from '../../services/user.service';


@Component({
  selector: 'app-lading-page',
  templateUrl: './landing-page.component.html'
})
export class LandingPageComponent implements OnInit {

  topTenUser;
  mostPopularBand;
  usersWhoSharedBands;

  constructor(private  httpService: HttpService) {
  }

  ngOnInit() {
    this.getTopTenUsers();
    this.getMostPopularBand();
  }

  getTopTenUsers(): void {
    this.httpService.get('/users/getTopTenUsersByBand')
      .subscribe((res: any) => {
        this.topTenUser = res ;
      });
  }

  getMostPopularBand(): void {
    this.httpService.get('/users/getMostPopularBand')
      .subscribe((res: any) => {
        this.mostPopularBand = res;
      });
  }

}
