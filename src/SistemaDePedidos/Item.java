package SistemaDePedidos;


class Item {
	private String nome;
	private double preco;

	public Item(String nomeItem, double precoItem) {
		this.nome = nomeItem;
		this.preco = precoItem;
	}

	public String getNome() {
		return nome;
	}

	public double getPreco() {
		return preco;
	}
}