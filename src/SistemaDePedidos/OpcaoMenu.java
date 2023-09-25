package SistemaDePedidos;

public enum OpcaoMenu {
	CRIAR_PEDIDO(1, "Criar Pedido"), ADICIONAR_ITEM(2, "Adicionar Item ao Pedido"),
	CALCULAR_TOTAL(3, "Calcular Total do Pedido"), LISTAR_ITENS(4, "Listar Itens do Pedido"), SAIR(5, "Sair");

	private final int valor;
	private final String descricao;

	OpcaoMenu(int valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public int getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public static OpcaoMenu fromValor(int valor) {
		for (OpcaoMenu opcao : values()) {
			if (opcao.valor == valor) {
				return opcao;
			}
		}

		return OpcaoMenu.SAIR;
	}

}
