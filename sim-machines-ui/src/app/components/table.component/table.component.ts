import {Component, Input} from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-custom-table',
  templateUrl: './table.component.html'
})
export class TableComponent {
  @Input()
  users;

  constructor(private router: Router, private userService: UserService) {}
  goToProfile(selectedUser): void {
    this.userService.user = selectedUser;
    this.router.navigate(['user-profile']);
  }
}
