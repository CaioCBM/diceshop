package br.unitins.diceshop.model;

import javax.validation.constraints.NotBlank;

public class Dado extends Entity<Dado> {

	private static final long serialVersionUID = -2892635712370827148L;
	
	private String descricao;
	private TipoDado tipoDado;
	private String cor;
	private Float preco;
	private Integer estoque;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoDado getTipoDado() {
		return tipoDado;
	}

	public void setTipoDado(TipoDado tipoDado) {
		this.tipoDado = tipoDado;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

}
