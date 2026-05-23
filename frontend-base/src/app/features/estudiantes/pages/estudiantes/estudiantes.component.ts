import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators
} from '@angular/forms';

@Component({
  selector: 'app-estudiantes',
  standalone: false,
  templateUrl: './estudiantes.component.html',
  styleUrls: ['./estudiantes.component.css']
})
export class EstudiantesComponent {

  /* PASOS DEL FORMULARIO */
  paso = 1;

  /* FORMULARIO */
  estudianteForm: FormGroup;

  /* LISTA */
  estudiantes: any[] = [];

  /* MODAL DETALLES */
  estudianteSeleccionado: any = null;

  /* EDITAR */
  editando = false;

  indiceEditando: number | null = null;

  constructor(private fb: FormBuilder) {

    this.estudianteForm = this.fb.group({

      nombre: ['', [Validators.required, Validators.minLength(3)]],
      apellido: ['', [Validators.required]],
      correo: ['', [Validators.required, Validators.email]],
      // Teléfono: debe comenzar con 9 y tener exactamente 9 dígitos
      telefono: ['', [Validators.required, Validators.pattern('^9\\d{8}$')]],
      edad: ['', [Validators.required, Validators.min(16), Validators.max(100)]],
      carrera: ['', [Validators.required]],
      codigo: ['', [Validators.required, Validators.minLength(4), Validators.pattern('^[A-Za-z0-9-]+$')]],
      fechaNacimiento: [''],
      genero: [''],
      direccion: ['', [Validators.required, Validators.minLength(10)]]

    });

  }

  onTelefonoInput(event: Event) {
    const input = event.target as HTMLInputElement;
    if (!input) return;
    // eliminar todo lo que no sean dígitos
    let v = input.value.replace(/\D/g, '');
    // recortar a 9 caracteres
    if (v.length > 9) v = v.slice(0, 9);
    // actualizar el input y el formulario sin disparar más eventos
    input.value = v;
    this.estudianteForm.get('telefono')?.setValue(v, { emitEvent: false });
  }

  get f() {
    return this.estudianteForm.controls;
  }

  isInvalid(controlName: string) {
    const c = this.estudianteForm.get(controlName);
    return !!(c && c.invalid && (c.dirty || c.touched));
  }

  isValid(controlName: string) {
    const c = this.estudianteForm.get(controlName);
    return !!(c && c.valid && (c.dirty || c.touched));
  }

  getErrorMessage(controlName: string) {
    const c = this.estudianteForm.get(controlName);
    if (!c || !c.errors) return '';
    if (c.errors['required']) return 'Este campo es obligatorio.';
    if (c.errors['minlength']) return `Mínimo ${c.errors['minlength'].requiredLength} caracteres.`;
    if (c.errors['email']) return 'Correo no válido.';
    if (c.errors['pattern']) {
      if (controlName === 'telefono') return 'El teléfono debe comenzar con 9 y tener exactamente 9 dígitos.';
      return 'Formato incorrecto.';
    }
    if (c.errors['duplicate']) return 'Ya existe un registro con este valor.';
    if (c.errors['min']) return `El valor mínimo es ${c.errors['min'].min}.`;
    if (c.errors['max']) return `El valor máximo es ${c.errors['max'].max}.`;
    return 'Valor inválido.';
  }

  isDuplicate(field: 'codigo' | 'correo') {
    const value = this.estudianteForm.get(field)?.value;
    if (!value) return false;
    return this.estudiantes.some((s, idx) => {
      if (this.editando && this.indiceEditando === idx) return false;
      return s[field] === value;
    });
  }

  validarPaso1() {
    return !!(
      this.estudianteForm.get('nombre')?.valid &&
      this.estudianteForm.get('apellido')?.valid &&
      this.estudianteForm.get('correo')?.valid &&
      this.estudianteForm.get('telefono')?.valid &&
      this.estudianteForm.get('edad')?.valid &&
      this.estudianteForm.get('codigo')?.valid
    );
  }

  irPaso2() {
    if (this.validarPaso1()) {
      this.paso = 2;
    } else {
      this.estudianteForm.markAllAsTouched();
    }
  }

  /* GUARDAR */
  guardarEstudiante() {

    if (this.estudianteForm.invalid) {
      this.estudianteForm.markAllAsTouched();
      return;
    }

    // comprobaciones de duplicados (codigo y correo)
    if (this.isDuplicate('codigo')) {
      this.estudianteForm.get('codigo')?.setErrors({ duplicate: true });
      this.estudianteForm.get('codigo')?.markAsTouched();
      return;
    }

    if (this.isDuplicate('correo')) {
      this.estudianteForm.get('correo')?.setErrors({ duplicate: true });
      this.estudianteForm.get('correo')?.markAsTouched();
      return;
    }

    if (this.editando) {

      this.estudiantes[this.indiceEditando!] =
        this.estudianteForm.value;

      this.editando = false;

      this.indiceEditando = null;

    } else {

      this.estudiantes.push(
        this.estudianteForm.value
      );

    }

    /* RESET */
    this.estudianteForm.reset();
    // limpiar estados
    this.estudianteForm.markAsPristine();
    this.estudianteForm.markAsUntouched();

    /* VOLVER AL PASO 1 */
    this.paso = 1;

  }

  /* EDITAR */
  editarEstudiante(estudiante: any, index: number) {

    this.estudianteForm.patchValue(estudiante);

    this.editando = true;

    this.indiceEditando = index;

    /* IR AL PASO 1 */
    this.paso = 1;

  }

  /* ELIMINAR */
  eliminarEstudiante(index: number) {

    this.estudiantes.splice(index, 1);

  }

}