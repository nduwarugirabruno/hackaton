import { NgOptimizedImage } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { AuthService } from '../services/auth/auth.service';
import { UserService } from "../services/user/user.service";

@Component({
    selector: 'app-home',
    standalone: true,
    imports: [NgOptimizedImage],
    templateUrl: './home.component.html',
    styleUrl: './home.component.scss',
})
export class HomeComponent implements OnInit {
    message!: string;

    private auth = inject(AuthService);
    private user = inject(UserService);

    ngOnInit(): void {
        // this.getAll();
    }

    hello() {
        this.auth.hello().subscribe({
            next: (response: string) => {
                console.log('response: ', response);
                this.message = response;
            },
            error: (error) => {
                console.error(error);
            }
        });
    }

    getAll() {
        this.user.getAll().subscribe({
            next: (response) => {
                console.log('response: ', response);
            },
            error: (error) => {
                console.error(error);
            }
        });
    }
}
