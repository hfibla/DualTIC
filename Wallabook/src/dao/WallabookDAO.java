package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Categoria;
import model.Libro;
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
	
	public boolean registrarUsuario (Usuario usuario) {
	    if (comprobarUsuario(usuario.getNickname()) == false) {
		EntityTransaction entityTransaction = this.getEntityManager().getTransaction();
		entityTransaction.begin();
		this.getEntityManager().persist(usuario);
		entityTransaction.commit();
		return true;
	    }
	    else
		return false;	    
	}
	
	public boolean comprobarUsuario (String nick) {
	    Query query = this.getEntityManager().createQuery("Select count(u) from Usuario u where u.nickname = :nick", Usuario.class);
	    query.setParameter("nick", nick);
	    Long count = (Long) query.getSingleResult();
	    return ( ( count.equals( 0L ) ) ? false : true );
	}
	
	public String comprobarLogin(String nick, String passwd) {
	    Query query = this.getEntityManager().createQuery("Select count(u) from Usuario u where u.nickname = :nick and u.password = :passwd", Usuario.class);
	    query.setParameter("nick", nick);
	    query.setParameter("passwd", passwd);
	    Long count = (Long) query.getSingleResult();
	    return ( ( count.equals( 0L ) ) ? "Error en los datos introducidos" : "�Login correcto!" );
	}
	
	public List<Usuario> consultarUsuarios() {
	    TypedQuery<Usuario> query = this.getEntityManager().createQuery("SELECT u FROM Usuario", Usuario.class);
	    List<Usuario> usuarios = query.getResultList();
	    return usuarios;
	}
	
	public void anadirLibro (Libro libro) {
	        EntityTransaction entityTransaction = this.getEntityManager().getTransaction();
	        entityTransaction.begin();
	        this.getEntityManager().persist(libro);
	        entityTransaction.commit();
	    }
	
	public List <Libro> consultarLibrosUsuario (Usuario usuario) {
	    List <Libro> libros = null;
	    Query queryCount = this.getEntityManager().createQuery("Select count (l) from Libro l where l.usuario = :user", Libro.class);
	    queryCount.setParameter("user", usuario);
	    Long count = (Long) queryCount.getSingleResult();
	    if (!count.equals(0L)) { 
		TypedQuery<Libro> queryAll = this.getEntityManager().createQuery("Select l from Libro l where l.usuario = :user", Libro.class);
		queryAll.setParameter("user", usuario);
		libros = queryAll.getResultList();
	    }
	    return libros;
	}
	
	public Libro consultarLibroID (String idLibro) {
	    Libro libro = null;
	    int idLibroParseada = Integer.parseInt(idLibro);
	    TypedQuery<Libro> query = this.getEntityManager().createQuery("Select l from Libro l where l.idLibro = :id", Libro.class);
	    query.setParameter("id", idLibroParseada);
	    libro = query.getSingleResult();
	    return libro;
	}
	public List <Libro> consultarLibrosAutor (String autor) {
	    List <Libro> libros = null;
	    Query queryCount = this.getEntityManager().createQuery("Select count (l) from Libro l where l.autor = :autor", Libro.class);
	    queryCount.setParameter("autor", autor);
	    Long count = (Long) queryCount.getSingleResult();
	    if (!count.equals(0L)) { 
		TypedQuery<Libro> queryAll = this.getEntityManager().createQuery("Select l from Libro l where l.autor = :autor", Libro.class);
		queryAll.setParameter("autor", autor);
		libros = queryAll.getResultList();
	    }
	    return libros;
	}
	
	public List<Categoria> obtenerCategorias(){
		List <Categoria> categorias = null;
		TypedQuery<Categoria> query = this.getEntityManager().createQuery("SELECT c FROM Categoria c", Categoria.class);
		categorias = query.getResultList();
		return categorias;			
	}
	
	public Usuario consultarUsuarioNickname (String nickname) {
	    Usuario usuario = null;
	    if (comprobarUsuario(nickname)) {
		TypedQuery <Usuario> query = this.getEntityManager().createQuery("Select u from Usuario where u.nickname = :nick", Usuario.class);
		query.setParameter("nick", nickname);
		usuario = query.getSingleResult();
	    }	    
	    return usuario;
	}
	
	public Long numLibrosNoDisponiblesUsuario(Usuario usuario){
	    Query queryCount = this.getEntityManager().createQuery("Select count (l) from Libro l where l.usuario = :user and l.disponible = 1", Libro.class);
	    queryCount.setParameter("user", usuario);
	    return (Long) queryCount.getSingleResult();
	}
	
	public String cambiarPropietarioLibro (Libro libro, Usuario antiguoPropietario, Usuario nuevoPropietario) {
	    String mensaje = "";
	    if (numLibrosNoDisponiblesUsuario(antiguoPropietario) <= 5) {
		libro.setUsuario(nuevoPropietario);
		EntityTransaction entityTransaction = this.getEntityManager().getTransaction();
	        entityTransaction.begin();
	        this.getEntityManager().persist(libro);
	        entityTransaction.commit();
	        mensaje = "Cambio de propietario realizado";
	    }
	    else {
		mensaje = "M�ximo de libros alcanzado";
	    }
	    return mensaje;
	}
	
	public Categoria consultarCategoriaNombre (String nombreCategoria) {
		TypedQuery<Categoria> query = this.getEntityManager().createQuery("SELECT c FROM Categoria c where c.nombreCategoria = :nombreCategoria", Categoria.class);
		query.setParameter("nombreCategoria", nombreCategoria);
		System.out.println(nombreCategoria);
		Categoria categoria = query.getSingleResult();
		return categoria;
	}
	
	public Categoria consultarCategoriaID (int idCategoria) {
		idCategoria = idCategoria - 1;
		TypedQuery<Categoria> query = this.getEntityManager().createQuery("SELECT c FROM Categoria c where c.idCategoria = :idCat", Categoria.class);
		query.setParameter("idCat", idCategoria);
		System.out.println(idCategoria);
		Categoria categoria = query.getSingleResult();
		return categoria;
	}
	
	
}
