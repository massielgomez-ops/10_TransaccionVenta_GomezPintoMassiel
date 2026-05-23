import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EnrollmentsComponent } from './features/enrollments/pages/enrollments/enrollments.component';
import { HomeComponent } from './features/home/pages/home/home.component';
import { CursosComponent } from './features/cursos/pages/cursos/cursos.component';
import { DashboardComponent } from './features/dashboard/pages/dashboard/dashboard.component';
import { PanelComponent } from './features/panel/pages/panel/panel.component';
import { EstudiantesComponent } from './features/estudiantes/pages/estudiantes/estudiantes.component';
import { DashboardVentasComponent } from './features/dashboard/pages/ventas/dashboard-ventas.component';
import { RegistroVentaComponent } from './features/ventas/pages/registro-venta/registro-venta.component';
import { ListadoVentasComponent } from './features/ventas/pages/listado-ventas/listado-ventas.component';
const routes: Routes = [

  {
    path: '',
    component: HomeComponent
  },

  {
    path: 'dashboard',
    component: DashboardComponent,
    children: [

      {
        path: '',
        component: PanelComponent
      },

      {
        path: 'enrollments',
        component: EnrollmentsComponent
      },

      {
        path: 'cursos',
        component: CursosComponent
      },
      
      {
        path: 'estudiantes',
        component: EstudiantesComponent
      },

      {
        path: 'ventas',
        component: DashboardVentasComponent
      }
    ]
  },

  {
    path: 'ventas/registro',
    component: RegistroVentaComponent
  },

  {
    path: 'ventas/listado',
    component: ListadoVentasComponent
  }
  ,
  {
    path: 'ventas',
    redirectTo: 'ventas/registro',
    pathMatch: 'full'
  }

];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})


export class AppRoutingModule { }
