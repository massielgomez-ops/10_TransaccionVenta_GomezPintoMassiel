package ap2.nombreapellido.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "students")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Campo obligatorio")
  @Size(min = 3, message = "Mínimo 3 caracteres")
  @Column(nullable = false)
  private String nombre;

  @NotBlank(message = "Campo obligatorio")
  @Column(nullable = false)
  private String apellido;

  @NotBlank(message = "Campo obligatorio")
  @Email(message = "Correo inválido")
  @Column(nullable = false)
  private String correo;

  @NotBlank(message = "Campo obligatorio")
  @Pattern(regexp = "^[0-9]{9}$", message = "Solo números y 9 dígitos")
  @Column(nullable = false, length = 9)
  private String telefono;

  @NotNull(message = "Campo obligatorio")
  @Min(value = 15, message = "El valor mínimo es 15")
  @Max(value = 100, message = "El valor máximo es 100")
  @Column(nullable = false)
  private Integer edad;

  @NotBlank(message = "Campo obligatorio")
  @Column(nullable = false)
  private String carrera;

  @NotBlank(message = "Campo obligatorio")
  @Size(min = 5, message = "Mínimo 5 caracteres")
  @Column(nullable = false)
  private String codigo;

  @NotBlank(message = "Campo obligatorio")
  @Size(min = 10, message = "Mínimo 10 caracteres")
  @Column(nullable = false, length = 255)
  private String direccion;

  public Student() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public Integer getEdad() {
    return edad;
  }

  public void setEdad(Integer edad) {
    this.edad = edad;
  }

  public String getCarrera() {
    return carrera;
  }

  public void setCarrera(String carrera) {
    this.carrera = carrera;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }
}