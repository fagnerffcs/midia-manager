package br.cin.ufpe.manager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="item")
public class ItemMidia {

	@Id
	@GeneratedValue
	@Column(name="itm_codigo")
	private Long id;
	
	@Column(name="itm_descricao")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="midia_fk")
	private Midia midia;

	public Midia getMidia() {
		return midia;
	}

	public void setMidia(Midia midia) {
		this.midia = midia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return id + "," + descricao;
	}
}
