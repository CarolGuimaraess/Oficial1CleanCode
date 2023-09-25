package SistemaDePedidos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaDePedidos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Pedido> pedidos = new ArrayList<>();
        MenuPedido menu = new MenuPedido(pedidos, scanner);

        menu.executarMenuPedidos();
    }
}
