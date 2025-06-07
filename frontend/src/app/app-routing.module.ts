import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { BotasPageComponent } from './pages/botas-page/botas-page.component';
import { CarritoPageComponent } from './pages/carrito-page/carrito-page.component';
import { CascosPageComponent } from './pages/cascos-page/cascos-page.component';
import { EscapesPageComponent } from './pages/escapes-page/escapes-page.component';
import { GuantesPageComponent } from './pages/guantes-page/guantes-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { MotosPageComponent } from './pages/motos-page/motos-page.component';
import { PoliticaPrivacidadPageComponent } from './pages/politica-privacidad-page/politica-privacidad-page.component';
import { RegisterPageComponent } from './pages/register-page/register-page.component';
import { RuedasPageComponent } from './pages/ruedas-page/ruedas-page.component';
import { TrajesMotoPageComponent } from './pages/trajes-moto-page/trajes-moto-page.component';
import { SobreNosotrosPageComponent } from './pages/sobre-nosotros-page/sobre-nosotros-page.component';
import { LikePageComponent } from './pages/like-page/like-page.component';

const routes: Routes = [
  {path: "", component: HomePageComponent},
  {path: "botas", component:BotasPageComponent},
  {path: "carrito", component:CarritoPageComponent},
  {path: "casco", component:CascosPageComponent},
  {path: "escapes", component:EscapesPageComponent},
  {path: "guantes", component:GuantesPageComponent},
  {path: "login", component: LoginPageComponent},
  {path: "motos", component: MotosPageComponent},
  {path: "politica-privacidad", component: PoliticaPrivacidadPageComponent},
  {path: "register", component: RegisterPageComponent},
  {path: "ruedas", component: RuedasPageComponent},
  {path: "trajes-moto", component: TrajesMotoPageComponent},
  {path: "sobreNosotros", component: SobreNosotrosPageComponent},
  {path: "mis-likes", component: LikePageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
