import { Component } from '@angular/core';
import { SignupComponent } from "./signup/signup.component";
import { SigninComponent } from "./signin/signin.component";

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [SignupComponent, SigninComponent],
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.scss'
})
export class AuthComponent {

}
