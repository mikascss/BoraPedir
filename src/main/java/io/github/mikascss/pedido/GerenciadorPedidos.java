package io.github.mikascss.pedido;

import java.util.ArrayList;
import java.util.HashMap;

public class GerenciadorPedidos {
    private HashMap<Integer, Pedido> pedidos = new HashMap<>();
    private int contadorPedidos = 1;

    public Pedido criarPedido(String nomeCliente){
        Pedido novoPedido = new Pedido(contadorPedidos, nomeCliente);
        pedidos.put(novoPedido.getNrIdentificador(), novoPedido);
        contadorPedidos++;
        return novoPedido;
    }

    public Pedido buscarPedido(int nrIdentificador){
        return pedidos.get(nrIdentificador);
    }

    public ArrayList<Pedido> getPedidos(){
        return new ArrayList<>(pedidos.values());
    }

    public double calcularTotalDoDia(){
        double soma = 0;
        for(Pedido pedido : pedidos.values()){
            soma += pedido.calcularTotal();
        }
        return soma;
     }

}
