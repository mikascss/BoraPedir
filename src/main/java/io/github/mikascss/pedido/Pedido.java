package io.github.mikascss.pedido;

import io.github.mikascss.cardapio.ItemCardapio;
import io.github.mikascss.cardapio.Lanche;
import lombok.Getter;

import java.util.ArrayList;

public class Pedido {
    @Getter
    private int nrIdentificador;
    @Getter
    private String nomeCliente;
    @Getter
    private ArrayList<ItemCardapio> itens = new ArrayList<>();

    public Pedido(int numero, String nomeCliente) {
        this.nrIdentificador = numero;
        setNomeCliente(nomeCliente);
    }

    public void setNomeCliente(String nomeCliente){
        if(nomeCliente == null || nomeCliente.isBlank()){
            throw new IllegalArgumentException("Nome do cliente não deve ser vazio ou nulo.");
        }
        this.nomeCliente = nomeCliente;
    }

    public void adicionarItem(ItemCardapio lanche){
        itens.add(lanche);
    }

    public double calcularTotal(){
        double total = 0;
        for (ItemCardapio lanche : itens) {
            total += lanche.getPreco();
        }
        return total;
    }
}
