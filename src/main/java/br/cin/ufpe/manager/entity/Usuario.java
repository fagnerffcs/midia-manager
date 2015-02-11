package br.cin.ufpe.manager.entity;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("USU")
public class Usuario extends Pessoa {
	
	@OneToMany(mappedBy="dono", fetch=FetchType.EAGER)
	private List<Midia> midias;

	public List<Midia> getMidias() {
		return midias;
	}

	public void setMidias(List<Midia> midias) {
		this.midias = midias;
	}

	@Override
	public String toString() {
		return getId() + "," + getNome() + "," + getCpf() + ",USU";
	}

}
