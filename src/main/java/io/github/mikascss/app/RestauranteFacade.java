package io.github.mikascss.app;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ArrayList;

import io.github.mikascss.cardapio.ItemCardapio;
import io.github.mikascss.cardapio.Lanche;
import io.github.mikascss.cardapio.LancheFactory;
import io.github.mikascss.pedido.Pedido;
import io.github.mikascss.pedido.GerenciadorPedidos;

public class RestauranteFacade {
    private final GerenciadorPedidos gerenciador = new GerenciadorPedidos();

    public HashMap<String, Double> getCardapio(){
        HashMap<String, Double> cardapio = new LinkedHashMap<>();
        cardapio.put("X-Burger", 16.00);
        cardapio.put("X-Salada", 18.00);
        cardapio.put("X-Bacon", 22.00);
        return cardapio;
    }

    public Pedido criarPedido(String nomeCliente) {
        return gerenciador.criarPedido(nomeCliente);
    }

    public void adicionarItem(int numeroPedido, String nomeLanche) {
        Pedido pedido = gerenciador.buscarPedido(numeroPedido);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado.");
        }
        Double preco = getCardapio().get(nomeLanche);
        if (preco == null) {
            throw new IllegalArgumentException("Lanche não encontrado no cardápio.");
        }

        pedido.adicionarItem(LancheFactory.criarLanche(nomeLanche));
    }

    public ArrayList<Pedido> getPedidos() {
        return gerenciador.getPedidos();
    }

    public double calcularTotalDoDia() {
        return gerenciador.calcularTotalDoDia();
    }

}
