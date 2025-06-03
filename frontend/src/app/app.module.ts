import { NgModule, CUSTOM_ELEMENTS_SCHEMA  } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
// import { SideBarComponent } from './components/side-bar/side-bar.component';
import { ToolbarComponent } from './components/toolbar/toolbar.component';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzBreadCrumbModule } from 'ng-zorro-antd/breadcrumb';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NzIconModule, NZ_ICONS  } from 'ng-zorro-antd/icon';
import { NzDropDownModule } from 'ng-zorro-antd/dropdown';
import { UserOutline } from '@ant-design/icons-angular/icons';
import { CarrouselComponent } from './components/homePageComponents/carrousel/carrousel.component';


import { NzCarouselModule } from 'ng-zorro-antd/carousel';
import { LoginPageComponent } from './login-page/login-page.component';




@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    // SideBarComponent,
    ToolbarComponent,
    CarrouselComponent,
    LoginPageComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NzLayoutModule,
    NzIconModule,
    NzCarouselModule,
    NzDropDownModule,
    NzBreadCrumbModule,
    AppRoutingModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [
    { provide: NZ_ICONS, useValue: [UserOutline] }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
