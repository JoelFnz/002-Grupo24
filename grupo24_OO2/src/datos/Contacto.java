package datos;

import java.util.Objects;

public class Contacto {
	private long idContacto;
	private String email;
	private String telefono;
	private Usuario usuario;
	
	public Contacto() {}
	
	public Contacto(String email, String telefono, Usuario usuario) {
		super();
		this.email = email;
		this.telefono = telefono;
		this.usuario = usuario;
	}

	public long getIdContacto() {
		return idContacto;
	}

	protected void setIdContacto(long idContacto) {
		this.idContacto = idContacto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Contacto [idContacto=" + idContacto + ", email=" + email + ", telefono=" + telefono + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, idContacto, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contacto other = (Contacto) obj;
		return Objects.equals(email, other.email) && idContacto == other.idContacto
				&& Objects.equals(telefono, other.telefono);
	}
	
}
