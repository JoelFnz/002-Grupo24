package datos;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Usuario {
    protected long idUsuario;
    protected int dni;
    protected String apellido;
    protected String nombre;
    protected LocalDate fechaDeNacimiento;
    protected boolean baja;
    protected Contacto contacto;
    
    // Nuevos atributos
    protected String username;  // Nuevo atributo para el nombre de usuario
    protected String password;  // Nuevo atributo para la contraseña

    public Usuario() {}

    public Usuario(int dni, String nombre, String apellido, LocalDate fechaDeNacimiento, Contacto contacto, String username, String password) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.contacto = contacto;
        this.username = username;
        this.password = password;
        baja = false;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    protected void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public boolean isBaja() {
        return baja;
    }

    public void setBaja(boolean baja) {
        this.baja = baja;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    // Métodos para los nuevos atributos (username y password)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario [idUsuario=" + idUsuario + ", dni=" + dni + ", apellido=" + apellido + ", nombre=" + nombre
                + ", fechaDeNacimiento=" + fechaDeNacimiento + ", baja=" + baja + ", contacto=" + contacto 
                + ", username=" + username + ", password=" + (password != null ? "******" : "null") + "]"; // No mostramos el password por seguridad
    }

    @Override
    public int hashCode() {
        return Objects.hash(apellido, baja, dni, fechaDeNacimiento, idUsuario, nombre, username, password);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        return Objects.equals(apellido, other.apellido) && baja == other.baja && dni == other.dni
                && Objects.equals(fechaDeNacimiento, other.fechaDeNacimiento) && idUsuario == other.idUsuario
                && Objects.equals(nombre, other.nombre) && Objects.equals(username, other.username)
                && Objects.equals(password, other.password);
    }
}

