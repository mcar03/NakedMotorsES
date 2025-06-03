import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { SobreNosotrosComponent } from './pages/sobre-nosotros/sobre-nosotros.component';

const routes: Routes = [
  {path: "", component: HomePageComponent},
  // {path: "/motos", component: }
  {path: "sobreNosotros", component: SobreNosotrosComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
