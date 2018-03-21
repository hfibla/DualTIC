package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the libros database table.
 * 
 */
@Entity
@Table(name = "libros")
@NamedQuery(name = "Libro.findAll", query = "SELECT l FROM Libro l")
public class Libro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_LIBRO")
	private int idLibro;

	private String autor;

	private int disponible;

	private String editorial;

	private String idioma;

	private String titulo;

	// bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name = "ID_CATEGORIA")
	private Categoria categoria;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	// bi-directional many-to-one association to Peticion
	@OneToMany(mappedBy = "libro")
	private List<Peticion> peticiones;

	public Libro() {
	}

	public Libro(String autor, int disponible, String idioma, String titulo, Categoria categoria, Usuario usuario) {
		super();
		this.autor = autor;
		this.disponible = disponible;
		this.idioma = idioma;
		this.titulo = titulo;
		this.categoria = categoria;
		this.usuario = usuario;
	}

	public Libro(String autor, int disponible, String editorial, String idioma, String titulo, Categoria categoria,
			Usuario usuario) {
		super();
		this.autor = autor;
		this.disponible = disponible;
		this.editorial = editorial;
		this.idioma = idioma;
		this.titulo = titulo;
		this.categoria = categoria;
		this.usuario = usuario;
	}

	public int getIdLibro() {
		return this.idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public String getAutor() {
		return this.autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getDisponible() {
		return this.disponible;
	}

	public void setDisponible(int disponible) {
		this.disponible = disponible;
	}

	public String getEditorial() {
		return this.editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getIdioma() {
		return this.idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Peticion> getPeticiones() {
		return this.peticiones;
	}

	public void setPeticiones(List<Peticion> peticiones) {
		this.peticiones = peticiones;
	}

	public Peticion addPeticione(Peticion peticione) {
		getPeticiones().add(peticione);
		peticione.setLibro(this);

		return peticione;
	}

	public Peticion removePeticione(Peticion peticione) {
		getPeticiones().remove(peticione);
		peticione.setLibro(null);

		return peticione;
	}

}