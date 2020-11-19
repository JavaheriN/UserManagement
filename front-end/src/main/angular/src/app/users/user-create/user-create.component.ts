import {Component, OnInit} from '@angular/core';
import {UserService} from '../user.service';
import {Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {NotificationType} from '../../notification-message';
import {NotificationService} from '../../notification.service';
import {Gender} from '../gender';

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent implements OnInit {
  form: FormGroup;
  public Gender = Gender;

  constructor(public userService: UserService,
              private router: Router, private  notificationService: NotificationService) {
  }

  ngOnInit(): void {
    this.form = new FormGroup({
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      emailAddress: new FormControl('', Validators.email),
      address: new FormControl('', ),
      phoneNumber: new FormControl('', ),
      gender: new FormControl(Gender.MALE, )
    });
  }

  // tslint:disable-next-line:typedef
  get formControl() {
    return this.form.controls;
  }

  // tslint:disable-next-line:typedef
  submit() {
    console.log(this.form.value);
    this.userService.create(this.form.value).subscribe(res => {
      this.notificationService.sendMessage({
        message: 'User created successfully!',
        type: NotificationType.success
      });
      this.router.navigateByUrl('users');
    });
  }

}
