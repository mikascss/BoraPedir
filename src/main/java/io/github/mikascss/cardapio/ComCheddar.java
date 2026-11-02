package io.github.mikascss.cardapio;

public class ComCheddar extends AdicionalDecorator {

    public ComCheddar(ItemCardapio item){
        super(item);
    }

    @Override
    public String getDescricao(){
        return item.getDescricao() + " + cheddar";
    }

    @Override
    public double getPreco(){
        return item.getPreco() + 4.00;
    }
}
