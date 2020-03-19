import { MailboxComponent } from './mailbox/mailbox.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './home/home.component';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TeamComponent } from './team/team.component';


const routes: Routes = [
  {
    path: '', component: HomeComponent
  },
  {
    path: 'anonymeMFXG63TZNVSQ', component: LoginComponent
  },
  {
    path: 'adminpanel', component: DashboardComponent
  },
  {
    path: 'joinus', component: TeamComponent
  },
  {
    path: 'adminpanel/mailbox', component: MailboxComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
