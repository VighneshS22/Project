import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Adminlogin } from 'src/app/models/adminlogin';

@Component({
   selector: 'app-admin-login',
   templateUrl: './admin-login.component.html',
   styleUrls: ['./admin-login.component.css'],
})
export class AdminLoginComponent implements OnInit {
   adminLogin: Adminlogin = new Adminlogin();

   constructor(private router: Router) {}

   ngOnInit(): void {}

   onSubmit() {
      if (this.adminLogin.id == 'Admin' && this.adminLogin.password == 'Admin') {
         sessionStorage.setItem('currentAdminId', this.adminLogin.id.toString());
         this.router.navigate(['/admin']);
      } else alert('Incorrect Details');
   }
}
