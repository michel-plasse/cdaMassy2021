package fr.cdamassy2021.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Personne {

	@Id
	private int idPersonne;
	private String prenom, nom, email, tel, pwd;

	/**
	 * Constructeur
	 *
	 * @param id
	 * @param prenom
	 * @param nom
	 * @param email
	 * @param tel
	 * @param pwd
	 */
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="membre_canal"
			,joinColumns = {@JoinColumn(name = "id_personne")}
			,inverseJoinColumns = {@JoinColumn(name="id_canal")})
	private Set<Canal> allCanauxMembre;
	
	public Personne(int id, String prenom, String nom, String email, String tel, String pwd) {
		this.idPersonne = id;
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.tel = tel;
		this.pwd = pwd;
	}

	public Personne(String prenom, String nom, String email, String tel, String pwd) {
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.tel = tel;
		this.pwd = pwd;
	}

	public Personne(int idPersonne, String prenom, String nom) {
		this.idPersonne = idPersonne;
		this.prenom = prenom;
		this.nom = nom;
	}

	public Personne() {

	}

	public int getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 89 * hash + this.idPersonne;
		hash = 89 * hash + Objects.hashCode(this.prenom);
		hash = 89 * hash + Objects.hashCode(this.nom);
		hash = 89 * hash + Objects.hashCode(this.email);
		hash = 89 * hash + Objects.hashCode(this.tel);
		hash = 89 * hash + Objects.hashCode(this.pwd);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Personne other = (Personne) obj;
		if (this.idPersonne != other.idPersonne) {
			return false;
		}
		if (!Objects.equals(this.prenom, other.prenom)) {
			return false;
		}
		if (!Objects.equals(this.nom, other.nom)) {
			return false;
		}
		if (!Objects.equals(this.email, other.email)) {
			return false;
		}
		if (!Objects.equals(this.tel, other.tel)) {
			return false;
		}
		if (!Objects.equals(this.pwd, other.pwd)) {
			return false;
		}
		return true;
	}

}
