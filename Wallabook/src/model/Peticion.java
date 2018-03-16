package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the peticiones database table.
 * 
 */
@Entity
@Table(name="peticiones")
@NamedQuery(name="Peticion.findAll", query="SELECT p FROM Peticion p")
public class Peticion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PeticionPK id;

	private int confirmada;

	//bi-directional many-to-one association to Libro
	@ManyToOne
	@JoinColumn(name="ID_LIBRO")
	private Libro libro;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_REMITENTE")
	private Usuario usuario;

	public Peticion() {
	}

	public PeticionPK getId() {
		return this.id;
	}

	public void setId(PeticionPK id) {
		this.id = id;
	}

	public int getConfirmada() {
		return this.confirmada;
	}

	public void setConfirmada(int confirmada) {
		this.confirmada = confirmada;
	}

	public Libro getLibro() {
		return this.libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}