import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';

@Component({
    selector: 'app-signin',
    standalone: true,
    imports: [ReactiveFormsModule],
    templateUrl: './signin.component.html',
    styleUrl: './signin.component.scss'
})
export class SigninComponent implements OnInit {
    signinForm!: FormGroup;

    private auth = inject(AuthService);
    private fb = inject(FormBuilder);
    private router = inject(Router);

    ngOnInit(): void {
        this.signinForm = this.fb.group({
            email: ['', [Validators.required, Validators.email]],
            password: ['', Validators.required]
        });
    }

    submit() {
        console.log(this.signinForm.value);
        if (this.signinForm.valid) {
            this.auth.authenticate(this.signinForm.value).subscribe({
                next: (response) => {
                    console.log('response: ', response);
                    const token = response.token;
                    localStorage.setItem('jwtToken', token);
                    this.router.navigateByUrl('home');
                },
                error: (error) => {
                    console.error('error: ', error);
                }
            });
        }
    }
}
