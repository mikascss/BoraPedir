package io.github.mikascss.cardapio;

public class Lanche implements ItemCardapio{
    private String nome;
    private double preco;

    public Lanche(String nome, double preco){
        setNome(nome);
        setPreco(preco);
    }

    @Override
    public String getDescricao(){
        return nome;
    }

    @Override
    public double getPreco(){
        return preco;
    }

    public void setNome(String nome){
        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("O nome do lanche não deve ser vazio ou nulo.");
        }
        this.nome = nome;
    }

    public void setPreco(double preco){
        if(preco < 0){
            throw new IllegalArgumentException("O preço do lanche não deve ser negativo.");
        }
        this.preco = preco;
    }
}
