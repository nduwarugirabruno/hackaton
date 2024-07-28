import { Component, OnInit, inject } from '@angular/core';
import {
    FormBuilder,
    FormGroup,
    ReactiveFormsModule,
    Validators,
} from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { Country } from '../../shared/entity/enum/country.enum';
import { Role } from '../../shared/entity/enum/role.enum';

@Component({
    selector: 'app-signup',
    standalone: true,
    imports: [ReactiveFormsModule],
    templateUrl: './signup.component.html',
    styleUrl: './signup.component.scss',
})
export class SignupComponent implements OnInit {
    registerForm!: FormGroup;

    private auth = inject(AuthService);
    private fb = inject(FormBuilder);
    countries = Object.values(Country);

    ngOnInit(): void {
        this.registerForm = this.fb.group(
            {
                firstName: ['', Validators.required],
                lastName: ['', Validators.required],
                country: ['', Validators.required],
                city: ['', Validators.required],
                firstLine: ['', Validators.required],
                secondLine: ['', Validators.nullValidator],
                phone: ['', Validators.required],
                email: ['', [Validators.required, Validators.email]],
                password: ['', Validators.required],
                // role: ['', Validators.required],
                confirmedPassword: ['', Validators.required],
            },
            { validator: this.passwordMatchValidator }
        );
    }

    submit() {
        console.log(this.registerForm.value);
        if (this.registerForm.valid) {
            this.auth.register(this.buildRegisterData()).subscribe({
                next: (response) => {
                    console.log('response: ', response);
                    // const token = response.token;
                    // localStorage.setItem('jwtToken', token);
                },
                error: (error) => {
                    console.error('error: ', error);
                },
            });
        }
    }

    buildRegisterData() {
        return {
            firstName: this.registerForm.value.firstName,
            lastName: this.registerForm.value.lastName,
            country: this.registerForm.value.country,
            city: this.registerForm.value.city,
            firstLine: this.registerForm.value.firstLine,
            secondLine: this.registerForm.value.secondLine,
            phone: +this.registerForm.value.phone,
            email: this.registerForm.value.email,
            password: this.registerForm.value.password,
            role: Role.USER_STANDARD,
        };
    }

    passwordMatchValidator(formGroup: FormGroup) {
        const password = formGroup.get('password')?.value;
        const confirmedPassword = formGroup.get('confirmedPassword')?.value;
        if (!password || password != confirmedPassword) {
            formGroup
                .get('confirmedPassword')
                ?.setErrors({ passwordMismatch: true });
        } else {
            formGroup.get('confirmedPassword')?.setErrors(null);
        }
    }
}
