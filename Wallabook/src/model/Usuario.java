package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_USUARIO")
	private int idUsuario;

	private String correo;

	private String localidad;

	private String nickname;

	@Column(name="NOMBRE_REAL")
	private String nombreReal;

	private String password;

	private String telefono;

	//bi-directional many-to-one association to Libro
	@OneToMany(mappedBy="usuario")
	private List<Libro> libros;

	//bi-directional many-to-one association to Peticion
	@OneToMany(mappedBy="usuario")
	private List<Peticion> peticiones;

	//bi-directional many-to-one association to Avatar
	@ManyToOne
	@JoinColumn(name="ID_AVATAR")
	private Avatar avatar;

	public Usuario() {
	}
	
	public Usuario(String nickname, String password) {
	    super();
	    this.nickname = nickname;
	    this.password = password;
	}



	public Usuario(String correo, String localidad, String nickname, String password) {
	    super();
	    this.correo = correo;
	    this.localidad = localidad;
	    this.nickname = nickname;
	    this.password = password;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNombreReal() {
		return this.nombreReal;
	}

	public void setNombreReal(String nombreReal) {
		this.nombreReal = nombreReal;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Libro> getLibros() {
		return this.libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	public Libro addLibro(Libro libro) {
		getLibros().add(libro);
		libro.setUsuario(this);

		return libro;
	}

	public Libro removeLibro(Libro libro) {
		getLibros().remove(libro);
		libro.setUsuario(null);

		return libro;
	}

	public List<Peticion> getPeticiones() {
		return this.peticiones;
	}

	public void setPeticiones(List<Peticion> peticiones) {
		this.peticiones = peticiones;
	}

	public Peticion addPeticion(Peticion peticion) {
		getPeticiones().add(peticion);
		peticion.setUsuario(this);

		return peticion;
	}

	public Peticion removePeticion(Peticion peticion) {
		getPeticiones().remove(peticion);
		peticion.setUsuario(null);

		return peticion;
	}

	public Avatar getAvatar() {
		return this.avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

}