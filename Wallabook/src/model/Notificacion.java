package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the notificaciones database table.
 * 
 */
@Entity
@Table(name = "notificaciones")
@NamedQuery(name = "Notificacion.findAll", query = "SELECT n FROM Notificacion n")
public class Notificacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_NOTIFICACION")
	private int idNotificacion;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	private int leido;

	private String mensaje;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	public Notificacion() {
	}

	public Notificacion(String mensaje, Usuario usuario) {
		super();
		this.mensaje = mensaje;
		this.usuario = usuario;
		this.fecha = new Date();
	}

	public int getIdNotificacion() {
		return this.idNotificacion;
	}

	public void setIdNotificacion(int idNotificacion) {
		this.idNotificacion = idNotificacion;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getLeido() {
		return this.leido;
	}

	public void setLeido(int leido) {
		this.leido = leido;
	}

	public String getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}