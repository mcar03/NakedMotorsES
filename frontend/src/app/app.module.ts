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
import { FormsModule } from '@angular/forms';

import { NzCarouselModule } from 'ng-zorro-antd/carousel';
import { FooterComponent } from './components/footer/footer.component';
import { PoliticaPrivacidadPageComponent } from './pages/politica-privacidad-page/politica-privacidad-page.component';
import { ListadoProductosGeneralComponent } from './components/homePageComponents/listado-productos-general/listado-productos-general.component';
import { MotosPageComponent } from './pages/motos-page/motos-page.component';
import { GuantesPageComponent } from './pages/guantes-page/guantes-page.component';
import { CascosPageComponent } from './pages/cascos-page/cascos-page.component';
import { RuedasPageComponent } from './pages/ruedas-page/ruedas-page.component';
import { EscapesPageComponent } from './pages/escapes-page/escapes-page.component';
import { BotasPageComponent } from './pages/botas-page/botas-page.component';
import { TrajesMotoPageComponent } from './pages/trajes-moto-page/trajes-moto-page.component';
import { RegisterPageComponent } from './pages/register-page/register-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { CarritoPageComponent } from './pages/carrito-page/carrito-page.component';
import { SobreNosotrosPageComponent } from './pages/sobre-nosotros-page/sobre-nosotros-page.component';
import { ListadoProductosMotosComponent } from './components/motosPageComponents/listado-productos-motos/listado-productos-motos.component';
import { EntradaImagenMotosComponentComponent } from './components/motosPageComponents/entrada-imagen-motos-component/entrada-imagen-motos-component.component';
import { EntradaImagenGuantesComponentComponent } from './components/guantesPageComponents/entrada-imagen-guantes-component/entrada-imagen-guantes-component.component';
import { EntradaImagenCascosComponentComponent } from './components/cascosPageComponents/entrada-imagen-cascos-component/entrada-imagen-cascos-component.component';
import { EntradaImagenRuedasComponentComponent } from './components/ruedasPageComponents/entrada-imagen-ruedas-component/entrada-imagen-ruedas-component.component';
import { EntradaImagenEscapesComponentComponent } from './components/escapesPageComponents/entrada-imagen-escapes-component/entrada-imagen-escapes-component.component';
import { EntradaImagenBotasComponentComponent } from './components/botasPageComponents/entrada-imagen-botas-component/entrada-imagen-botas-component.component';
import { EntradaImagenTrajeMotoComponentComponent } from './components/trajesMotoPageComponents/entrada-imagen-traje-moto-component/entrada-imagen-traje-moto-component.component';
import { ListadoBotasComponent } from './components/botasPageComponents/listado-botas/listado-botas.component';
import { PoliticaPrivacidadComponentComponent } from './components/politica-privacidad-component/politica-privacidad-component.component';




@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    // SideBarComponent,
    ToolbarComponent,
    CarrouselComponent,
    FooterComponent,
    PoliticaPrivacidadPageComponent,
    ListadoProductosGeneralComponent,
    MotosPageComponent,
    GuantesPageComponent,
    CascosPageComponent,
    RuedasPageComponent,
    EscapesPageComponent,
    BotasPageComponent,
    TrajesMotoPageComponent,
    RegisterPageComponent,
    LoginPageComponent,
    CarritoPageComponent,
    SobreNosotrosPageComponent,
    ListadoProductosMotosComponent,
    EntradaImagenMotosComponentComponent,
    EntradaImagenGuantesComponentComponent,
    EntradaImagenCascosComponentComponent,
    EntradaImagenRuedasComponentComponent,
    EntradaImagenEscapesComponentComponent,
    EntradaImagenBotasComponentComponent,
    EntradaImagenTrajeMotoComponentComponent,
    ListadoBotasComponent,
    PoliticaPrivacidadComponentComponent,
    
    
    
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NzLayoutModule,
    NzIconModule,
    FormsModule,
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
