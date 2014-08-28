package com.farodrigues.teste_walmart.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mapa")
public class Mapa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	private String nome;

	@OneToMany(mappedBy = "mapa")
	private Set<Rota> rotas;

	public Mapa() {
	}
	
	public Mapa(String nome) {
		this.nome = nome;
	}

	public Mapa(String nome, Set<Rota> rotas) {
		this.nome = nome;
		this.rotas = rotas;
	}

	public Set<Rota> getRotas() {
		if (rotas == null) {
			rotas = new HashSet<Rota>();
		}
		return rotas;
	}

	public void addRota(Rota rota) {
		getRotas().add(rota);
	}

	public void addRotas(Collection<Rota> rotas) {
		getRotas().addAll(rotas);
	}

	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mapa other = (Mapa) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}