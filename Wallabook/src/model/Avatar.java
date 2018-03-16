package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the avatares database table.
 * 
 */
@Entity
@Table(name="avatares")
@NamedQuery(name="Avatar.findAll", query="SELECT a FROM Avatar a")
public class Avatar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_AVATAR")
	private int idAvatar;

	@Column(name="LINK_AVATAR")
	private String linkAvatar;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="avatar")
	private List<Usuario> usuarios;

	public Avatar() {
	}

	public Avatar(int idAvatar) {
		super();
		this.idAvatar = idAvatar;
	}

	public int getIdAvatar() {
		return this.idAvatar;
	}

	public void setIdAvatar(int idAvatar) {
		this.idAvatar = idAvatar;
	}

	public String getLinkAvatar() {
		return this.linkAvatar;
	}

	public void setLinkAvatar(String linkAvatar) {
		this.linkAvatar = linkAvatar;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setAvatar(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setAvatar(null);

		return usuario;
	}

}