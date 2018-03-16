package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the peticiones database table.
 * 
 */
@Embeddable
public class PeticionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date fecha;

	@Column(name="ID_REMITENTE", insertable=false, updatable=false)
	private int idRemitente;

	@Column(name="ID_LIBRO", insertable=false, updatable=false)
	private int idLibro;

	public PeticionPK() {
	}
	public java.util.Date getFecha() {
		return this.fecha;
	}
	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}
	public int getIdRemitente() {
		return this.idRemitente;
	}
	public void setIdRemitente(int idRemitente) {
		this.idRemitente = idRemitente;
	}
	public int getIdLibro() {
		return this.idLibro;
	}
	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PeticionPK)) {
			return false;
		}
		PeticionPK castOther = (PeticionPK)other;
		return 
			this.fecha.equals(castOther.fecha)
			&& (this.idRemitente == castOther.idRemitente)
			&& (this.idLibro == castOther.idLibro);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fecha.hashCode();
		hash = hash * prime + this.idRemitente;
		hash = hash * prime + this.idLibro;
		
		return hash;
	}
}