import {AppComponent} from './app.component';
import {UserProfileComponent } from './views/Users/profile/user-profile.component';
import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {LandingPageComponent} from './views/LadingPage/landing-page.component';


const routes: Routes = [
  { path: '', redirectTo: '/landing-page', pathMatch: 'full' },
  { path: 'landing-page', component: LandingPageComponent },
  { path: 'user-profile', component: UserProfileComponent }
];


@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
