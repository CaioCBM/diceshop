package br.unitins.diceshop.model;

public class Endereco extends Entity<Endereco> {
		
	private static final long serialVersionUID = 7763411439738849913L;
	
		private String cep;
		private String endereco;
		
		//Usar ParseInt para transformar CEP em int
		//Exemplo: int x = Integer.parseInt("12");

		public String getCep() {
			return cep;
		}
		public void setCep(String cep) {
			this.cep = cep;
		}
		public String getEndereco() {
			return endereco;
		}
		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}
}
