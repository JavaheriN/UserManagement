import {Component, OnInit} from '@angular/core';
import {User} from '../user';
import {UserService} from '../user.service';
import {NotificationService} from '../../notification.service';
import {NotificationType} from '../../notification-message';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: User[] = [];

  constructor(public userService: UserService, private notificationService: NotificationService) {
  }

  ngOnInit(): void {
    this.readUsers();
  }

  // tslint:disable-next-line:typedef
  readUsers(): void {
    this.userService.getAll().subscribe((data: User[]) => {
        this.users = data;
      },
      error => {
        this.notificationService.sendMessage({
          message: error,
          type: NotificationType.error
        });
      });
  }


// tslint:disable-next-line:typedef
  deleteUser(id) {
    this.userService.delete(id).subscribe(res => {
      this.users = this.users.filter(item => item.id !== id);
      this.notificationService.sendMessage({
        message: 'User deleted successfully!',
        type: NotificationType.success
      });

    });
  }


}
