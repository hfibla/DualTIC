package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


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

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

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
	
	public Peticion(PeticionPK id) {
		super();
		this.id = id;
	}



	public PeticionPK getId() {
		return this.id;
	}

	public void setId(PeticionPK id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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