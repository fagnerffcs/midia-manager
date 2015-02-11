package br.cin.ufpe.manager.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Midia {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="mid_id")
	private Long id;
	
	@Column(name="mid_nome")
	private String nome;
	
	@OneToMany(mappedBy="midia")
	private List<ItemMidia> itens;
	
	@ManyToOne
	@JoinColumn(name="usuario_fk")
	private Usuario dono;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public List<ItemMidia> getItens() {
		return itens;
	}

	public void setItens(List<ItemMidia> itens) {
		this.itens = itens;
	}

	public Usuario getDono() {
		return dono;
	}

	public void setDono(Usuario dono) {
		this.dono = dono;
	}

	@Override
	public String toString() {
		return id + "," + nome;
	}

}
