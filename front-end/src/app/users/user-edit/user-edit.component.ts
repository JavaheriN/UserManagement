import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../user.service';
import {Router, ActivatedRoute} from '@angular/router';
import {NotificationService} from '../../notification.service';
import {Gender} from '../gender';
import {NotificationType} from '../../notification-message';
import {User} from '../user';
import {EnumValue} from '@angular/compiler-cli/src/ngtsc/partial_evaluator';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {
  id: number;
  form: FormGroup;
  public Gender = Gender;

  constructor(public userService: UserService, private router: Router,
              private activatedRoute: ActivatedRoute, private  notificationService: NotificationService) {
  }

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.params.id;
    this.form = new FormGroup({
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      emailAddress: new FormControl('', Validators.email),
      address: new FormControl('',),
      phoneNumber: new FormControl('',),
      gender: new FormControl('',)
    });

    this.userService.find(this.id).subscribe((data: User) => {
      console.log(data);
      this.form.setValue({
        firstName: data.firstName,
        lastName: data.lastName,
        emailAddress: data.emailAddress,
        address: data.address,
        phoneNumber: data.phoneNumber,
        gender: Gender[data.gender]
      });

    });
  }

// tslint:disable-next-line:typedef
  get formControl() {
    return this.form.controls;
  }

  // tslint:disable-next-line:typedef
  submit() {
    console.log(this.form.value);
    this.userService.update(this.id, this.form.value).subscribe(res => {
      this.notificationService.sendMessage({
        message: 'User updated successfully!',
        type: NotificationType.success
      });
      this.router.navigateByUrl('users');
    });
  }
}
