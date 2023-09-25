package SistemaDePedidos;

import java.util.List;
import java.util.Scanner;

public class MenuPedido {

	private List<Pedido> pedidos;
	private Scanner scanner;

	public MenuPedido(List<Pedido> pedidos, Scanner scanner) {
		this.pedidos = pedidos;
		this.scanner = scanner;
	}

	// M�todo principal que inicia a intera��o do usu�rio com o sistema de pedidos.
	// Esse M�todo chama todas as fun��es criadas.
	public void executarMenuPedidos() {
		while (true) {
			exibirMenu();
			OpcaoMenu opcao = lerEscolhaDoUsuario();

			switch (opcao) {
			case CRIAR_PEDIDO:
				criarPedido();
				break;
			case ADICIONAR_ITEM:
				adicionarItemPedido();
				break;
			case CALCULAR_TOTAL:
				calcularTotalDoPedido();
				break;
			case LISTAR_ITENS:
				listarItensDoPedido();
				break;
			case SAIR:
				encerrarPrograma();
			}
		}
	}

	// Encerra o programa.
	public void encerrarPrograma() {
		System.out.println("Saindo do sistema.");
		System.exit(0);
	}

	/**
	 * L� uma escolha v�lida do usu�rio a partir da entrada padr�o e a retorna como
	 * uma op��o de menu.
	 */
	private OpcaoMenu lerEscolhaDoUsuario() {
		while (true) {
			try {
				System.out.print("Escolha uma op��o: ");
				int escolha = Integer.parseInt(scanner.nextLine());

				for (OpcaoMenu opcao : OpcaoMenu.values()) {
					if (opcao.getValor() == escolha) {
						return opcao;
					}
				}
				/*
				 * Exibe uma mensagem de erro quando o usu�rio fornece uma escolha que n�o
				 * corresponde a nenhuma op��o do menu
				 */
				System.out.println("Op��o inv�lida. Escolha uma op��o do menu.");
			} catch (NumberFormatException e) {
				// Exibe uma mensagem de erro se a entrada do usu�rio for diferente de n�mero
				System.out.println("Op��o inv�lida. S� � permitido n�mero. Escolha uma op��o do menu.");
			}
		}
	}

	// Exibe as op��es do menu para o usu�rio de acordo com o enum OpcaoMenu
	public void exibirMenu() {
		for (OpcaoMenu opcao : OpcaoMenu.values()) {
			System.out.println(opcao.getValor() + ". " + opcao.getDescricao());
		}
	}

	// Cria um novo pedido e adiciona � lista de pedidos.
	public void criarPedido() {
		pedidos.add(new Pedido());
		System.out.println("Pedido criado.");
	}

	// Adiciona um item ao pedido atual.
	public void adicionarItemPedido() {
		if (!temPedidoAtual()) {
			System.out.println("Crie um pedido primeiro.");
			return;
		}

		System.out.print("Digite o nome do item: ");
		String nomeItem = scanner.nextLine();

		double precoItem;
		System.out.print("Digite o pre�o do item: ");
		if (scanner.hasNextDouble() && (precoItem = scanner.nextDouble()) > 0) {
			Item item = new Item(nomeItem, precoItem);
			Pedido ultimoPedido = pedidos.get(pedidos.size() - 1);
			ultimoPedido.adicionarItem(item);
			System.out.println("Item adicionado ao pedido.");

			scanner.nextLine(); // Limpar entrada inv�lida.
		} else {
			// Exibe uma mensagem de erro se a entrada do pre�o do item for inv�lida
			System.out.println("Entrada inv�lida para o pre�o do item. "
					+ "S� � permitido n�mero positivo e utilize v�rgula (,) para valores decimais.");
			scanner.nextLine(); // Limpar entrada inv�lida.
		}

	}

	// Calcula e exibe o total do pedido atual.
	public void calcularTotalDoPedido() {
		if (!temPedidoAtual()) {
			System.out.println("Crie um pedido primeiro.");
			return;
		}

		Pedido pedidoAtual = pedidos.get(pedidos.size() - 1);
		double total = pedidoAtual.calcularTotal();
		System.out.println("Total do pedido: " + total);
	}

	// Lista os itens do pedido atual.
	public void listarItensDoPedido() {
		if (!temPedidoAtual()) {
			System.out.println("Crie um pedido primeiro.");
			return;
		}

		Pedido pedidoParaListar = pedidos.get(pedidos.size() - 1);
		List<Item> itensDoPedido = pedidoParaListar.getItens();
		System.out.println("Itens do pedido:");
		for (Item item : itensDoPedido) {
			System.out.println(item.getNome() + ": " + item.getPreco());
		}
	}

	// Verifica se h� um pedido.
	private boolean temPedidoAtual() {
		return !pedidos.isEmpty();
	}
}
