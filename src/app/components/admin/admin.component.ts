import { Router } from '@angular/router';
import { Feedbacks } from './../../models/feedbacks';
import { Complaints } from './../../models/complaints';
import { Customers } from './../../models/customers';
import { Engineers } from './../../models/engineers';
import { Managers } from './../../models/managers';
import { AdminService } from './../../services/admin.service';
import { Component, OnInit } from '@angular/core';

@Component({
   selector: 'app-admin',
   templateUrl: './admin.component.html',
   styleUrls: ['./admin.component.css'],
})
export class AdminComponent implements OnInit {
   viewManagersStatus: boolean = false;
   viewEngineersStatus: boolean = false;
   viewCustomersStatus: boolean = false;
   viewComplaintsStatus: boolean = false;
   viewFeedbacksStatus: boolean = false;
   anyButtonClicked: boolean = false;

   updateManagerStatus: boolean = false;
   managerUpdating: Managers = new Managers();
   updateCustomerStatus: boolean = false;
   customerUpdating: Customers = new Customers();
   updateComplaintStatus: boolean = false;
   complaintUpdating: Complaints = new Complaints();
   updateFeedbackStatus: boolean = false;
   feedbackUpdating: Feedbacks = new Feedbacks();

   managers: Managers[] = [];
   engineers: Engineers[] = [];
   customers: Customers[] = [];
   complaints: Complaints[] = [];
   feedbacks: Feedbacks[] = [];

   constructor(private _adminService: AdminService, private route: Router) {}

   ngOnInit(): void {}

   logout() {
      alert('You Logged out Successfully');
      this.route.navigate(['home']);
   }
   // ---------------------------- View -------------------------

   viewManagers() {
      this.viewManagersStatus = true;
      this.viewEngineersStatus = false;
      this.viewCustomersStatus = false;
      this.viewComplaintsStatus = false;
      this.viewFeedbacksStatus = false;
      this.anyButtonClicked = false;
      this.anyButtonClicked = true;
      this._adminService.getAllManagers().subscribe((data) => {
         this.managers = data;
      });
   }
   viewEngineers() {
      this.viewManagersStatus = false;
      this.viewComplaintsStatus = false;
      this.viewCustomersStatus = false;
      this.viewFeedbacksStatus = false;
      this.anyButtonClicked = false;
      this.viewEngineersStatus = true;
      this.anyButtonClicked = true;
      this._adminService.getAllEngineers().subscribe((data) => {
         this.engineers = data;
      });
   }
   viewCustomers() {
      this.viewManagersStatus = false;
      this.viewEngineersStatus = false;
      this.viewCustomersStatus = false;
      this.viewCustomersStatus = false;
      this.viewComplaintsStatus = false;
      this.viewFeedbacksStatus = false;
      this.anyButtonClicked = false;
      this.viewCustomersStatus = true;
      this.anyButtonClicked = true;
      this._adminService.getAllCustomers().subscribe((data) => {
         this.customers = data;
      });
   }
   viewComplaints() {
      this.viewManagersStatus = false;
      this.viewEngineersStatus = false;
      this.viewCustomersStatus = false;
      this.viewFeedbacksStatus = false;
      this.anyButtonClicked = false;
      this.viewComplaintsStatus = true;
      this.anyButtonClicked = true;
      this._adminService.getAllComplaints().subscribe((data) => {
         this.complaints = data;
      });
   }
   viewFeedbacks() {
      this.viewManagersStatus = false;
      this.viewEngineersStatus = false;
      this.viewCustomersStatus = false;
      this.viewComplaintsStatus = false;
      this.anyButtonClicked = false;
      this.viewFeedbacksStatus = true;
      this.anyButtonClicked = true;
      this._adminService.getAllFeedbacks().subscribe((data) => {
         this.feedbacks = data;
      });
   }

   // ---------------------------- Delete ---------------------
   deleteManager(manager: Managers) {
      this._adminService.deleteManager(manager.managerEmail).subscribe(() => {
         alert('deleted');
      });
   }

   deleteEngineer(engineer: Engineers) {
      this._adminService.deleteEngineer(engineer.engineerEmail).subscribe(() => {
         alert('deleted');
      });
   }
   deleteCustomer(customer: Customers) {
      this._adminService.deleteCustomer(customer.customerEmail).subscribe(() => {
         alert('deleted');
      });
   }
   deleteComplaint(complaint: Complaints) {
      this._adminService.deleteComplaint(complaint.ticketId).subscribe(() => {
         alert('deleted');
      });
   }
   deleteFeedback(feedback: Feedbacks) {
      console.log(feedback.feedbackId);

      this._adminService.deleteFeedback(feedback.feedbackId).subscribe(() => {
         alert('deleted');
      });
   }

   // ---------------------------- Update -------------------------------

   // manager
   updateManager(manager: Managers) {
      this.updateManagerStatus = true;
      this.managerUpdating = manager;
   }
   newManagerUpdates() {
      console.log('pincode : ', this.managerUpdating.managerPincode);
      this._adminService.newManagerUpdates(this.managerUpdating.managerPincode, this.managerUpdating.managerEmail).subscribe((data) => {
         if (data) {
            alert('Updated successfully !');
            this.updateManagerStatus = false;
         }
      });
   }

   // customer
   updateCustomer(customer: Customers) {
      this.updateCustomerStatus = true;
      this.customerUpdating = customer;
   }
   newCustomerUpdates() {
      console.log('pincode : ', this.customerUpdating.customerPincode);
      this._adminService.newCustomerUpdates(this.customerUpdating.customerPincode, this.customerUpdating.customerEmail).subscribe((data) => {
         if (data) {
            alert('Updated successfully !');
            this.updateCustomerStatus = false;
         }
      });
   }

   //complaint
   updateComplaint(complaint: Complaints) {
      this.updateComplaintStatus = true;
      this.complaintUpdating = complaint;
   }
   newComplaintUpdates() {
      this._adminService.newComplaintUpdates(this.complaintUpdating).subscribe((data) => {
         if (data) {
            alert('Updated successfully !');
            this.updateComplaintStatus = false;
         }
      });
   }
   //feedback
   updateFeedback(feedback: Feedbacks) {
      this.updateFeedbackStatus = true;
      this.feedbackUpdating = feedback;
   }
   newFeedbackUpdates() {
      this._adminService.newFeedbackUpdates(this.feedbackUpdating).subscribe((data) => {
         if (data) {
            alert('Updated successfully !');
            this.updateFeedbackStatus = false;
         }
      });
   }
}
