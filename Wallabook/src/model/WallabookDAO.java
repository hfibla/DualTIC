package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jspAlumnos");
		this.setEntityManager(entityManagerFactory.createEntityManager());
	}
	
	public List<Libro> consultarLibros() {
		TypedQuery<Libro> query = this.getEntityManager().createQuery("SELECT l FROM Libro l", Libro.class);
		List<Libro> libros = query.getResultList();
		return libros;
	}
	
	public void registrarUsuario (Usuario usuario) {
	    if (comprobarUsuario(usuario.getNickname()) == false) {
		EntityTransaction entityTransaction = this.getEntityManager().getTransaction();
		entityTransaction.begin();
		this.getEntityManager().persist(usuario);
		entityTransaction.commit();
	    }
	    else
		System.out.println("Ese usuario ya existe");	    
	}
	
	public boolean comprobarUsuario (String nick) {
	    TypedQuery<Usuario> query = this.getEntityManager().createQuery("Select u from Usuario u where u.nickname = :nick",
			Usuario.class);
	Usuario usuario = query.setParameter("nick", nick).getSingleResult();
	    if (usuario.equals(null))
		return false;
	    else
		return true;
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
	
}
