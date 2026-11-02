package io.github.mikascss.app;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.HashSet;

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
        adicionarItem(numeroPedido, criarLanche(nomeLanche));
    }

    public Lanche criarLanche(String nomeLanche) {
        return LancheFactory.criarLanche(nomeLanche);
    }

    public void adicionarItem(int numeroPedido, ItemCardapio item) {
        Pedido pedido = gerenciador.buscarPedido(numeroPedido);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado.");
        }
        if (item == null) {
            throw new IllegalArgumentException("O item não pode ser nulo.");
        }

        pedido.adicionarItem(item);
    }

    public Pedido buscarPedido(int numero) {
        return gerenciador.buscarPedido(numero);
    }

    public void removerItem(int numeroPedido, int numeroItem) {
        Pedido pedido = buscarPedido(numeroPedido);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado.");
        }
        pedido.removerItem(numeroItem);
    }

    public void cancelarPedido(int numero) {
        gerenciador.cancelarPedido(numero);
    }

    public HashSet<String> getCombinacoesDoDia() {
        return gerenciador.getCombinacoesDoDia();
    }

    public ArrayList<Pedido> getPedidos() {
        return gerenciador.getPedidos();
    }

    public void registrarGorjeta(int numeroPedido, double valor) {
        Pedido pedido = buscarPedido(numeroPedido);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado.");
        }
        pedido.setGorjeta(valor);
    }

    public double calcularTotalGorjetas() {
        double total = 0;
        for (Pedido pedido : gerenciador.getPedidos()) {
            total += pedido.getGorjeta();
        }
        return total;
    }

    public double calcularTotalComGorjetas() {
        return calcularTotalDoDia() + calcularTotalGorjetas();
    }

    public double calcularTotalDoDia() {
        return gerenciador.calcularTotalDoDia();
    }

}
