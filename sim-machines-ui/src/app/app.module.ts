import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {ComponentsModule} from './components/components.module';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { HttpService} from './services/http.service';
import { AppRoutingModule } from './app-routing.module';

//3rd party components
import { DataTablesModule } from 'angular-datatables';
import { LoadingBarModule } from '@ngx-loading-bar/core';
//views
import { UserProfileComponent} from './views/Users/profile/user-profile.component';
import {LandingPageComponent} from './views/LadingPage/landing-page.component';
import {UserService} from './services/user.service';

@NgModule({
  declarations: [
    AppComponent,
    UserProfileComponent,
    LandingPageComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    DataTablesModule,
    FormsModule,
    ComponentsModule,
    AppRoutingModule,
    LoadingBarModule.forRoot(),
  ],
  providers: [HttpService, UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
