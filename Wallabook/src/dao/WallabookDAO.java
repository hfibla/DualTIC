package dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Avatar;
import model.Categoria;
import model.Libro;
import model.Notificacion;
import model.Peticion;
import model.PeticionPK;
import model.Usuario;

public class WallabookDAO {

	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public WallabookDAO() {
		super();
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Wallabook");
		this.setEntityManager(entityManagerFactory.createEntityManager());
	}

	public List<Libro> consultarLibros() {
		TypedQuery<Libro> query = this.getEntityManager().createQuery("SELECT l FROM Libro l", Libro.class);
		List<Libro> libros = query.getResultList();
		return libros;
	}

	public boolean registrarUsuario(Usuario usuario) {
		if ((comprobarUsuario(usuario.getNickname()) && comprobarEmail(usuario.getCorreo())) == false) {
			EntityTransaction entityTransaction = this.getEntityManager().getTransaction();
			entityTransaction.begin();
			this.getEntityManager().persist(usuario);
			entityTransaction.commit();
			return true;
		} else
			return false;
	}

	public boolean comprobarUsuario(String nick) {
		Query query = this.getEntityManager().createQuery("Select count(u) from Usuario u where u.nickname = :nick",
				Usuario.class);
		query.setParameter("nick", nick);
		Long count = (Long) query.getSingleResult();
		return ((count.equals(0L)) ? false : true);
	}

	public boolean comprobarEmail(String email) {
		Query query = this.getEntityManager().createQuery("Select count(u) from Usuario u where u.correo = :email",
				Usuario.class);
		query.setParameter("email", email);
		Long count = (Long) query.getSingleResult();
		return ((count.equals(0L)) ? false : true);
	}

	public boolean comprobarLogin(String nick, String passwd) {
		Query query = this.getEntityManager().createQuery(
				"Select count(u) from Usuario u where u.nickname = :nick and u.password = :passwd", Usuario.class);
		query.setParameter("nick", nick);
		query.setParameter("passwd", passwd);
		Long count = (Long) query.getSingleResult();
		return ((count.equals(0L)) ? false : true);
	}

	public List<Usuario> consultarUsuarios() {
		TypedQuery<Usuario> query = this.getEntityManager().createQuery("SELECT u FROM Usuario", Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		return usuarios;
	}

	public void anadirLibro(Libro libro) {
		EntityTransaction entityTransaction = this.getEntityManager().getTransaction();
		entityTransaction.begin();
		this.getEntityManager().persist(libro);
		entityTransaction.commit();
	}

	public List<Libro> consultarLibrosUsuario(Usuario usuario) {
		List<Libro> libros = Collections.emptyList();
		Query queryCount = this.getEntityManager().createQuery("Select count (l) from Libro l where l.usuario = :user",
				Libro.class);
		queryCount.setParameter("user", usuario);
		Long count = (Long) queryCount.getSingleResult();
		if (!count.equals(0L)) {
			TypedQuery<Libro> queryAll = this.getEntityManager()
					.createQuery("Select l from Libro l where l.usuario = :user", Libro.class);
			queryAll.setParameter("user", usuario);
			libros = queryAll.getResultList();
		}
		return libros;
	}

	public Libro consultarLibroID(String idLibro) {
		Libro libro = null;
		int idLibroParseada = Integer.parseInt(idLibro);
		TypedQuery<Libro> query = this.getEntityManager().createQuery("Select l from Libro l where l.idLibro = :id",
				Libro.class);
		query.setParameter("id", idLibroParseada);
		libro = query.getSingleResult();
		return libro;
	}

	public List<Libro> consultarLibrosAutor(String autor) {
		List<Libro> libros = Collections.emptyList();
		Query queryCount = this.getEntityManager().createQuery("Select count (l) from Libro l where l.autor = :autor",
				Libro.class);
		queryCount.setParameter("autor", autor);
		Long count = (Long) queryCount.getSingleResult();
		if (!count.equals(0L)) {
			TypedQuery<Libro> queryAll = this.getEntityManager()
					.createQuery("Select l from Libro l where l.autor = :autor", Libro.class);
			queryAll.setParameter("autor", autor);
			libros = queryAll.getResultList();
		}
		return libros;
	}

	public List<Categoria> obtenerCategorias() {
		List<Categoria> categorias = Collections.emptyList();
		TypedQuery<Categoria> query = this.getEntityManager().createQuery("SELECT c FROM Categoria c", Categoria.class);
		categorias = query.getResultList();
		return categorias;
	}

	public Usuario consultarUsuarioNickname(String nickname) {
		Usuario usuario = null;
		if (comprobarUsuario(nickname)) {
			TypedQuery<Usuario> query = this.getEntityManager()
					.createQuery("Select u from Usuario u where u.nickname = :nick", Usuario.class);
			query.setParameter("nick", nickname);
			usuario = query.getSingleResult();
		}
		return usuario;
	}

	public Long numLibrosNoDisponiblesUsuario(Usuario usuario) {
		Query queryCount = this.getEntityManager()
				.createQuery("Select count (l) from Libro l where l.usuario = :user and l.disponible = 0", Libro.class);
		queryCount.setParameter("user", usuario);
		return (Long) queryCount.getSingleResult();
	}

	public boolean cambiarPropietarioLibro(Libro libro, Usuario antiguoPropietario, Usuario nuevoPropietario) {
		if (numLibrosNoDisponiblesUsuario(nuevoPropietario) <= 5) {
			Libro libroEditado = this.getEntityManager().find(Libro.class, libro.getIdLibro());
			this.getEntityManager().getTransaction().begin();
			libroEditado.setUsuario(nuevoPropietario);
			libroEditado.setDisponible(0);
			this.getEntityManager().getTransaction().commit();
			gestionarPeticionLibro(libro, nuevoPropietario);
			return true;
		} else {
			return false;
		}
	}

	public void enviarNotificacionesCambioLibroOK(Libro libro, Usuario antiguoPropietario, Usuario nuevoPropietario) {
		Notificacion notificacionExPropietario = new Notificacion(
				"El cambio referente a su libro " + libro.getTitulo() + "se ha realizado con éxito.",
				antiguoPropietario);
		Notificacion notificacionNuevoPropietario = new Notificacion(
				"La petición del libro " + libro.getTitulo() + " que solicitó se ha realizado con éxito.",
				nuevoPropietario);
		EntityTransaction entityTransaction = this.getEntityManager().getTransaction();
		entityTransaction.begin();
		this.getEntityManager().persist(notificacionExPropietario);
		this.getEntityManager().persist(notificacionNuevoPropietario);
		entityTransaction.commit();
	}

	public void enviarNotificacionesCambioLibroError(Libro libro, Usuario antiguoPropietario,
			Usuario nuevoPropietario) {
		Notificacion notificacionExPropietario = new Notificacion(
				"El cambio referente a su libro " + libro.getTitulo() + "no ha podido realizarse.", antiguoPropietario);
		Notificacion notificacionNuevoPropietario = new Notificacion("La petición del libro " + libro.getTitulo()
				+ " que solicitó no ha podido realizarse. Demasiados libros no disponibles.", nuevoPropietario);
		EntityTransaction entityTransaction = this.getEntityManager().getTransaction();
		entityTransaction.begin();
		this.getEntityManager().persist(notificacionExPropietario);
		this.getEntityManager().persist(notificacionNuevoPropietario);
		entityTransaction.commit();
	}

	public void enviarNotificacionesOtrosCambioLibroDenegado(Libro libro,
			Usuario nuevoPropietario) {
		List<Peticion> peticiones = Collections.emptyList();
		Query queryCount = this.getEntityManager().createQuery(
				"Select count (p) from Peticion p where p.id.idRemitente != :nick and p.id.idLibro = :libro",
				Peticion.class);
		queryCount.setParameter("nick", nuevoPropietario.getNickname());
		queryCount.setParameter("libro", libro);
		Long count = (Long) queryCount.getSingleResult();
		if (!count.equals(0L)) {
			TypedQuery<Peticion> query = this.getEntityManager().createQuery(
					"Select p from Peticion p where p.id.idRemitente != :nick and p.id.idLibro = :libro",
					Peticion.class);
			query.setParameter("nick", nuevoPropietario.getNickname());
			query.setParameter("libro", libro);
			peticiones = query.getResultList();
			for (int i = 0; i < peticiones.size(); i++) {
				Usuario usuario = peticiones.get(i).getUsuario();
				Notificacion notificacionDenegada = new Notificacion(
						"La petición del libro " + libro.getTitulo() + " que solicitó ha sido denegada.",
						usuario);
				EntityTransaction entityTransaction = this.getEntityManager().getTransaction();
				entityTransaction.begin();
				this.getEntityManager().persist(notificacionDenegada);
				entityTransaction.commit();
			}
		}
	}
	
	public void denegarPeticion (Libro libro, Usuario usuario) {
		Query queryCount = this.getEntityManager().createQuery("Select count (p) from Peticion p where p.libro = :libro and p.id.idRemitente = :user", Peticion.class);
		queryCount.setParameter("libro", libro);
		queryCount.setParameter("user", usuario);
		Long count = (Long) queryCount.getSingleResult();
		if (!count.equals(0L)) {
			TypedQuery <Peticion> query = this.getEntityManager().createQuery("Select p from Peticion p where p.libro = :libro and p.id.idRemitente = :user", Peticion.class);
			query.setParameter("libro", libro);
			query.setParameter("user", usuario);
			Peticion peticionDenegada = query.getSingleResult();
			Peticion peticion = this.getEntityManager().find(Peticion.class, peticionDenegada.getId());
			this.getEntityManager().getTransaction().begin();			
				peticion.getId().setConfirmada("denegada");
			this.getEntityManager().getTransaction().commit();
			Notificacion notificacionDenegada = new Notificacion(
					"La petición del libro " + libro.getTitulo() + " que solicitó ha sido denegada.",
					usuario);
			EntityTransaction entityTransaction = this.getEntityManager().getTransaction();
			entityTransaction.begin();
			this.getEntityManager().persist(notificacionDenegada);
			entityTransaction.commit();			
		}
	}

	public List<Peticion> consultarPeticionesLibro(Libro libro) {
		List<Peticion> peticiones = Collections.emptyList();
		Query queryCount = this.getEntityManager().createQuery(
				"Select count (p) from Peticion p where p.libro = :libro and p.id.confirmada = 'pendiente' order by p.fecha desc",
				Peticion.class);
		queryCount.setParameter("libro", libro);
		Long count = (Long) queryCount.getSingleResult();
		if (!count.equals(0L)) {
			TypedQuery<Peticion> queryAll = this.getEntityManager().createQuery(
					"Select p from Peticion p where p.libro = :libro and p.id.confirmada = 'pendiente' order by p.fecha desc",
					Peticion.class);
			queryAll.setParameter("libro", libro);
			peticiones = queryAll.getResultList();
		}
		return peticiones;
	}

	public void gestionarPeticionLibro(Libro libro, Usuario nuevoPropietario) {
		List<Peticion> peticiones = consultarPeticionesLibro(libro);
		boolean porEncontrar = true;
		for (int i = 0; i < peticiones.size(); i++) {
			PeticionPK idPeticion = peticiones.get(i).getId();
			Peticion peticion = this.getEntityManager().find(Peticion.class, idPeticion);
			this.getEntityManager().getTransaction().begin();
			if (porEncontrar && peticion.getId().getIdRemitente() == nuevoPropietario.getIdUsuario()) {
				peticion.getId().setConfirmada("aceptada");
				porEncontrar = false;
			} else {
				peticion.getId().setConfirmada("denegada");
			}
			this.getEntityManager().getTransaction().commit();
		}
	}

	public Categoria consultarCategoriaNombre(String nombreCategoria) {
		TypedQuery<Categoria> query = this.getEntityManager()
				.createQuery("SELECT c FROM Categoria c where c.nombreCategoria = :nombreCategoria", Categoria.class);
		query.setParameter("nombreCategoria", nombreCategoria);
		Categoria categoria = query.getSingleResult();
		return categoria;
	}

	public Categoria consultarCategoriaID(int idCategoria) {
		idCategoria = idCategoria - 1;
		TypedQuery<Categoria> query = this.getEntityManager()
				.createQuery("SELECT c FROM Categoria c where c.idCategoria = :idCat", Categoria.class);
		query.setParameter("idCat", idCategoria);
		Categoria categoria = query.getSingleResult();
		return categoria;
	}

	public List<Libro> consultarLibrosDisponiblesNoPropios(Usuario usuario) {
		List<Libro> libros = Collections.emptyList();
		Query queryCount = this.getEntityManager().createQuery(
				"Select count (l) from Libro l where l.usuario != :user and l.disponible = 1", Libro.class);
		queryCount.setParameter("user", usuario);
		Long count = (Long) queryCount.getSingleResult();
		if (!count.equals(0L)) {
			TypedQuery<Libro> queryAll = this.getEntityManager()
					.createQuery("Select l from Libro l where l.usuario != :user and l.disponible = 1", Libro.class);
			queryAll.setParameter("user", usuario);
			libros = queryAll.getResultList();
		}

		return libros;
	}

	public Usuario actualizarDatosUsuario(Usuario usuario, String nombreReal, String telefono, String localidad) {
		if (usuario.getNombreReal() != nombreReal || usuario.getTelefono() != telefono
				|| usuario.getLocalidad() != localidad) {
			Usuario usuarioEditado = this.getEntityManager().find(Usuario.class, usuario.getIdUsuario());
			this.getEntityManager().getTransaction().begin();
			usuarioEditado.setNombreReal(nombreReal);
			usuarioEditado.setTelefono(telefono);
			usuarioEditado.setLocalidad(localidad);
			this.getEntityManager().getTransaction().commit();
			return usuarioEditado;
		} else
			return null;
	}

	public List<Libro> buscarLibrosTitulo(String titulo, Usuario usuario) {
		List<Libro> libros = Collections.emptyList();
		Query queryCount = this.getEntityManager().createQuery(
				"Select count (l) from Libro l where l.titulo = :titulo and l.disponible = 1 and l.usuario !=:user",
				Libro.class);
		queryCount.setParameter("user", usuario);
		queryCount.setParameter("titulo", titulo);
		Long count = (Long) queryCount.getSingleResult();
		if (!count.equals(0L)) {
			TypedQuery<Libro> queryAll = this.getEntityManager().createQuery(
					"Select l from Libro l where l.titulo = :titulo and l.disponible = 1 and l.usuario !=:user",
					Libro.class);
			queryAll.setParameter("user", usuario);
			queryAll.setParameter("titulo", titulo);
			libros = queryAll.getResultList();
		}
		return libros;
	}

	// public List <Libro> buscarLibrosAvanzado (String titulo, String autor, String
	// categoriaInput, Usuario usuario){
	public List<Libro> buscarLibrosAvanzado(String titulo, String autor, Categoria categoria, Usuario usuario) {
		List<Libro> libros = Collections.emptyList();
		Query queryCountAvanzada = this.getEntityManager().createQuery(
				"Select count (l) from Libro l where l.disponible = 1 and l.usuario !=:user and l.titulo like :titulo and l.autor like :autor and "

						+ " l.categoria like :categoria ",

				// + "l.categoria like :categoriaInput ",

				Libro.class);
		queryCountAvanzada.setParameter("user", usuario);
		if (titulo != null) {
			queryCountAvanzada.setParameter("titulo", titulo);
		} else {
			titulo = "%";
		}
		queryCountAvanzada.setParameter("autor", autor);
		// queryCountAvanzada.setParameter("categoria", categoriaInput);
		queryCountAvanzada.setParameter("categoria", categoria);
		Long count = (Long) queryCountAvanzada.getSingleResult();
		if (!count.equals(0L)) {
			TypedQuery<Libro> queryAvanzada = this.getEntityManager().createQuery(
					"Select l from Libro l where l.disponible = 1 and l.usuario !=:user and l.titulo like :titulo and l.autor like :autor and "

							+ " l.categoria like :categoria ",

					// + "l.categoria like :categoriaInput ",

					Libro.class);
			queryAvanzada.setParameter("user", usuario);
			queryAvanzada.setParameter("titulo", titulo);
			queryAvanzada.setParameter("autor", autor);
			// queryAvanzada.setParameter("categoria", categoriaInput);
			queryAvanzada.setParameter("categoria", categoria);
			libros = queryAvanzada.getResultList();
		}
		return libros;
	}

	public void cambiarDisponibilidadLibros(String[] idLibros, Usuario usuario) {
		List<Libro> libros = consultarLibrosUsuario(usuario);
		for (int i = 0; i < libros.size(); i++) {
			boolean encontrado = false;
			int idLibro = libros.get(i).getIdLibro();
			for (int j = 0; j < idLibros.length; j++) {
				if (idLibro == Integer.parseInt(idLibros[j]))
					encontrado = true;
			}
			Libro libroEditado = this.getEntityManager().find(Libro.class, idLibro);
			this.getEntityManager().getTransaction().begin();
			if (encontrado) {
				libroEditado.setDisponible(1);
			} else {
				libroEditado.setDisponible(0);
			}
			this.getEntityManager().getTransaction().commit();
		}

	}

	public void cambiarAvatar(Usuario usuario, Avatar avatar) {
		Usuario usuarioEditado = this.getEntityManager().find(Usuario.class, usuario.getIdUsuario());
		this.getEntityManager().getTransaction().begin();
		usuarioEditado.setAvatar(avatar);
		this.getEntityManager().getTransaction().commit();
	}

	public List<Avatar> obtenerAvatares() {
		List<Avatar> avatares = Collections.emptyList();
		Query queryCount = this.getEntityManager().createQuery("Select count (a) from Avatar a", Avatar.class);
		Long count = (Long) queryCount.getSingleResult();
		if (!count.equals(0L)) {
			TypedQuery<Avatar> queryAll = this.getEntityManager().createQuery("Select a from Avatar a", Avatar.class);
			avatares = queryAll.getResultList();
		}
		return avatares;
	}

}
